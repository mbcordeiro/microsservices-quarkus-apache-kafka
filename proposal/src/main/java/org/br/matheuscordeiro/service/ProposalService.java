package org.br.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.matheuscordeiro.dto.ProposalDetailsDto;

@ApplicationScoped
public interface ProposalService {
    ProposalDetailsDto findDetailsById(Long id);

    void create(ProposalDetailsDto proposalDetailsDto);

    void delete(Long id);
}
