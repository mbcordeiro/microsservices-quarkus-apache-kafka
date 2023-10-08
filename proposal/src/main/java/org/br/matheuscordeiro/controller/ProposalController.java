package org.br.matheuscordeiro.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.br.matheuscordeiro.dto.ProposalDetailsDto;
import org.br.matheuscordeiro.service.ProposalService;

@Path("/api/proposals")
@Slf4j
public class ProposalController {
    private final ProposalService proposalService;

    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @GET
    @Path("/{id}")
    public ProposalDetailsDto findDetails(@PathParam("id") Long id) {
        return proposalService.findDetailsById(id);
    }

    @POST
    public Response create(ProposalDetailsDto proposalDetailsDto) {
        log.info("Creating proposal");
        try {
            proposalService.create(proposalDetailsDto);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            proposalService.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
