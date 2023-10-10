package org.br.matheuscordeiro.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.br.matheuscordeiro.service.ReportService;

import java.time.LocalDateTime;

@ApplicationScoped
@Path("/api/opportunity")
public class ReportController {
    @Inject
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GET
    @Path("/report")
    @RolesAllowed({"user","manager"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateReport(){
        try {
            return Response.ok(reportService.generateCsvOpportunityReport(),
                            MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition",
                            "attachment; filename = "+ LocalDateTime.now() +"--opportunities-sales.csv").
                    build();
        } catch (ServerErrorException errorException) {
            return Response.serverError().build();
        }

    }

    @GET
    @Path("/data")
    @RolesAllowed({"user","manager"})
    public Response generateOpportunitiesData(){

        try {

            return Response.ok(reportService.getOpportunitiesData(),
                    MediaType.APPLICATION_JSON).build();

        } catch (ServerErrorException errorException) {

            return Response.serverError().build();

        }

    }

}
