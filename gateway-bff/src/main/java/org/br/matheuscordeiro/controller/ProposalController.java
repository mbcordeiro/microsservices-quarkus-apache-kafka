package org.br.matheuscordeiro.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.br.matheuscordeiro.dto.ProposalDetailsDto;
import org.br.matheuscordeiro.service.ProposalService;

@Path("/api/trades")
public class ProposalController {
    @Inject
    private final ProposalService proposalService;

    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "manager"})
    public Response getProposalDetailsById(@PathParam("id") long id) {
        try {
            return Response.ok(proposalService.getProposalDetailsById(id), MediaType.APPLICATION_JSON).build();
        } catch (ServerErrorException errorException) {
            return Response.serverError().build();
        }
    }

    @POST
    @RolesAllowed("proposal-customer")
    public Response createNewProposal(ProposalDetailsDto proposalDetails) {
        proposalService.create(proposalDetails);
        return Response.ok().build();
    }

    @DELETE
    @Path("/remove/{id}")
    @RolesAllowed("manager")
    public Response removeProposal(@PathParam("id") long id) {
        proposalService.delete(id);
        return Response.ok().build();
    }
}
