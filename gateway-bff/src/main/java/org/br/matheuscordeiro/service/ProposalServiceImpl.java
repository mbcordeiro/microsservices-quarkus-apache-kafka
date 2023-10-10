package org.br.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.br.matheuscordeiro.client.ProposalRestClient;
import org.br.matheuscordeiro.dto.ProposalDetailsDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ProposalServiceImpl implements ProposalService{
    @RestClient
    @Inject
    private final ProposalRestClient proposalRestClient;

    public ProposalServiceImpl(ProposalRestClient proposalRestClient) {
        this.proposalRestClient = proposalRestClient;
    }

    @Override
    public ProposalDetailsDto getProposalDetailsById(Long proposalId) {
        return proposalRestClient.getProposalDetailsById(proposalId);
    }

    @Override
    public void create(ProposalDetailsDto proposalDetailsDto) {
        proposalRestClient.create(proposalDetailsDto);
    }

    @Override
    public void delete(Long id) {
        proposalRestClient.delete(id);
    }
}
