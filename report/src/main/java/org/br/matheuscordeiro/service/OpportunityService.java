package org.br.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.matheuscordeiro.dto.OpportunityDto;
import org.br.matheuscordeiro.dto.ProposalDto;
import org.br.matheuscordeiro.dto.QuotationDto;

import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
public interface OpportunityService {
    void buildOpportunity(ProposalDto proposalDto);
    void saveQuotation(QuotationDto quotationDto);
    List<OpportunityDto> generateOpportunityDate();
    ByteArrayInputStream generateCsvReport();
}
