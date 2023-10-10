package org.br.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.matheuscordeiro.dto.OpportunityDto;
import org.br.matheuscordeiro.dto.ProposalDto;
import org.br.matheuscordeiro.dto.QuotationDto;
import org.br.matheuscordeiro.entity.Opportunity;
import org.br.matheuscordeiro.entity.Quotation;
import org.br.matheuscordeiro.repository.OpportunityRepository;
import org.br.matheuscordeiro.repository.QuotationRepository;
import org.eclipse.microprofile.opentracing.Traced;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Traced
public class OpportunityServiceImpl implements OpportunityService {
    private final QuotationRepository quotationRepository;
    private final OpportunityRepository opportunityRepository;

    public OpportunityServiceImpl(QuotationRepository quotationRepository, OpportunityRepository opportunityRepository) {
        this.quotationRepository = quotationRepository;
        this.opportunityRepository = opportunityRepository;
    }

    @Override
    public void buildOpportunity(ProposalDto proposalDto) {
        final var quotationList = quotationRepository.findAll().list();
        Collections.reverse(quotationList);
        final var opportunity = Opportunity.builder().date(LocalDateTime.now()).proposalId(proposalDto.proposalId())
                .customer(proposalDto.customer()).priceTonne(proposalDto.priceTonne())
                .lastCurrencyQuotation(quotationList.stream().findFirst().orElseThrow().getCurrencyPrice()).build();
        opportunityRepository.persist(opportunity);
    }

    @Override
    public void saveQuotation(QuotationDto quotationDto) {
        quotationRepository.persist(new Quotation(LocalDateTime.now(), quotationDto.currencyPrice()));
    }

    @Override
    public List<OpportunityDto> generateOpportunityDate() {
        final var opportunityList = new ArrayList<OpportunityDto>();
        opportunityRepository.findAll().stream().forEach(opportunity -> {
            opportunityList.add(OpportunityDto.builder().proposalId(opportunity.getProposalId())
                    .customer(opportunity.getCustomer()).priceTonne(opportunity.getPriceTonne())
                    .lastCurrencyQuotation(opportunity.getLastCurrencyQuotation()).build());
        });
        return opportunityList;
    }
}
