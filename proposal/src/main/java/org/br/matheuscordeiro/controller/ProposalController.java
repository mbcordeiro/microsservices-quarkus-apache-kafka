package org.br.matheuscordeiro.controller;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.br.matheuscordeiro.dto.ProposalDetailsDto;
import org.br.matheuscordeiro.service.ProposalService;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api/proposals")
@Slf4j
@Authenticated
public class ProposalController {
    private final ProposalService proposalService;
    private final JsonWebToken jsonWebToken;

    public ProposalController(ProposalService proposalService, JsonWebToken jsonWebToken) {
        this.proposalService = proposalService;
        this.jsonWebToken = jsonWebToken;
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"user","manager"})
    public ProposalDetailsDto findDetails(@PathParam("id") Long id) {
        return proposalService.findDetailsById(id);
    }

    @POST
    @RolesAllowed("manager")
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
    @RolesAllowed("manager")
    public Response delete(@PathParam("id") Long id) {
        try {
            proposalService.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
