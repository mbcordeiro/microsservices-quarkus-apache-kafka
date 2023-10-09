package org.br.matheuscordeiro.controller;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.br.matheuscordeiro.dto.OpportunityDto;
import org.br.matheuscordeiro.service.OpportunityService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.time.LocalDateTime;
import java.util.List;

@Authenticated
@Path("/api/opportunities")
public class OpportunityController {
    private final OpportunityService opportunityService;

    private final JsonWebToken token;

    public OpportunityController(OpportunityService opportunityService, JsonWebToken token) {
        this.opportunityService = opportunityService;
        this.token = token;
    }

    @GET
    @Path("/data")
    @RolesAllowed({"user","manager"})
    public List<OpportunityDto> generateReport(){
        return opportunityService.generateOpportunityData();
    }

}
