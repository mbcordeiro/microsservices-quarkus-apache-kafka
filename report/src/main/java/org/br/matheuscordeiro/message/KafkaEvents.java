package org.br.matheuscordeiro.message;

import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.br.matheuscordeiro.dto.ProposalDto;
import org.br.matheuscordeiro.dto.QuotationDto;
import org.br.matheuscordeiro.service.OpportunityService;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@Slf4j
public class KafkaEvents {
    private final OpportunityService opportunityService;

    public KafkaEvents(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @Incoming("proposal")
    @Transactional
    public void consumeProposal(ProposalDto proposalDto) {
        log.info("Consume new proposal in topic kafka");
        opportunityService.buildOpportunity(proposalDto);
    }

    @Incoming("proposal")
    @Blocking
    public void  consumeQuotation(QuotationDto quotationDto) {
        log.info("Consume new quotation in topic kafka");
        opportunityService.saveQuotation(quotationDto);
    }
}
