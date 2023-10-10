package org.br.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.matheuscordeiro.dto.OpportunityDto;

import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
public interface ReportService {
    ByteArrayInputStream generateCsvOpportunityReport();
    List<OpportunityDto> getOpportunitiesData();
}
