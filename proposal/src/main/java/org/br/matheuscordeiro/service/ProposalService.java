package org.br.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.matheuscordeiro.dto.ProposalDetailsDto;
import org.br.matheuscordeiro.dto.ProposalDto;

@ApplicationScoped
public interface ProposalService {
    ProposalDetailsDto findFullProposal(Long id);

    void createNewProposal(ProposalDetailsDto proposalDetailsDto);

    void removeProposal(Long id);
}
