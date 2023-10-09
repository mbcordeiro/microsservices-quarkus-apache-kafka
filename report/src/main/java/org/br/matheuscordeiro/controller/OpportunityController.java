package org.br.matheuscordeiro.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.br.matheuscordeiro.service.OpportunityService;

import java.time.LocalDateTime;

@Path("/api/opportunities")
public class OpportunityController {
    private final OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateReport() {
        try {
            return Response.ok(opportunityService.generateCsvReport(), MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "attachment; filename = " + LocalDateTime.now() +
                            "--opportunity-sales.csv").build();
        } catch (ServerErrorException e) {
            return Response.serverError().build();
        }
    }
}
