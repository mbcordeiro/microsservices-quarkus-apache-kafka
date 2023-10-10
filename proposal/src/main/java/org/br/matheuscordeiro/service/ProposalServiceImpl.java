package org.br.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.br.matheuscordeiro.dto.ProposalDetailsDto;
import org.br.matheuscordeiro.dto.ProposalDto;
import org.br.matheuscordeiro.entity.Proposal;
import org.br.matheuscordeiro.message.KafkaEvents;
import org.br.matheuscordeiro.repository.ProposalRepository;
import org.eclipse.microprofile.opentracing.Traced;

import java.time.LocalDateTime;

@ApplicationScoped
@Traced
public class ProposalServiceImpl implements ProposalService {
    private final ProposalRepository proposalRepository;

    private final KafkaEvents kafkaEvents;

    public ProposalServiceImpl(ProposalRepository proposalRepository, KafkaEvents kafkaEvents) {
        this.proposalRepository = proposalRepository;
        this.kafkaEvents = kafkaEvents;
    }

    @Override
    public ProposalDetailsDto findDetailsById(Long id) {
        final var proposal = proposalRepository.findById(id);
        return ProposalDetailsDto.builder().proposalId(proposal.getId())
                .proposalValidityDays(proposal.getProposalValidityDays()).country(proposal.getCountry())
                .priceTonne(proposal.getPriceTonne()).customer(proposal.getCustomer()).tonnes(proposal.getTonnes())
                .build();
    }

    @Override
    @Transactional
    public void create(ProposalDetailsDto proposalDetailsDto) {
        kafkaEvents.sendNewKafkaEvents(saveNewProposal(proposalDetailsDto));
    }

    @Override
    public void delete(Long id) {
        proposalRepository.deleteById(id);
    }

    @Transactional
    private ProposalDto saveNewProposal(ProposalDetailsDto proposalDetailsDto) {
        try {
            final var proposal = Proposal.builder().created(LocalDateTime.now())
                    .proposalValidityDays(proposalDetailsDto.proposalValidityDays()).country(proposalDetailsDto.country())
                    .customer(proposalDetailsDto.customer()).priceTonne(proposalDetailsDto.priceTonne())
                    .tonnes(proposalDetailsDto.tonnes()).build();
            proposalRepository.persist(proposal);
            return ProposalDto.builder()
                    .proposalId(proposalRepository.findByCustomer(proposal.getCustomer()).orElseThrow().getId())
                    .priceTonne(proposal.getPriceTonne()).customer(proposal.getCustomer()).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
