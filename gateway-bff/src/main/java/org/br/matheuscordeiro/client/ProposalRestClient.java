package org.br.matheuscordeiro.client;

import io.quarkus.oidc.token.propagation.reactive.AccessTokenRequestReactiveFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import org.br.matheuscordeiro.dto.ProposalDetailsDto;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/proposals")
@RegisterRestClient
@RegisterProvider(AccessTokenRequestReactiveFilter.class)
@ApplicationScoped
public interface ProposalRestClient {
    @GET
    @Path("/{id}")
    ProposalDetailsDto getProposalDetailsById(@PathParam("id") long proposalId);

    @POST
    void create(ProposalDetailsDto proposalDetails);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") long id);
}
