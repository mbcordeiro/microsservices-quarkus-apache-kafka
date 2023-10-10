package org.br.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.br.matheuscordeiro.dto.ProposalDetailsDto;

@ApplicationScoped
public interface ProposalService {
    ProposalDetailsDto getProposalDetailsById(Long proposalId);
    void create(ProposalDetailsDto proposalDetailsDto);
    void delete(Long id);
}
