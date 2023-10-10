package org.br.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.br.matheuscordeiro.client.ReportRestClient;
import org.br.matheuscordeiro.dto.OpportunityDto;
import org.br.matheuscordeiro.utils.CsvHelper;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
@Traced
public class ReportServiceImpl implements ReportService{
    @Inject
    @RestClient
    private final ReportRestClient reportRestClient;

    public ReportServiceImpl(ReportRestClient reportRestClient) {
        this.reportRestClient = reportRestClient;
    }

    @Override
    public ByteArrayInputStream generateCsvOpportunityReport() {
        List<OpportunityDto> opportunityData = reportRestClient.requestOpportunitiesData();
        return CsvHelper.opportunitiesToCsv(opportunityData);
    }

    @Override
    public List<OpportunityDto> getOpportunitiesData() {
        return reportRestClient.requestOpportunitiesData();
    }
}
