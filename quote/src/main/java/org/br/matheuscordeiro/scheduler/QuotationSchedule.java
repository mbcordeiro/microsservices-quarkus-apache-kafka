package org.br.matheuscordeiro.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.br.matheuscordeiro.service.QuotationService;

@ApplicationScoped
public class QuotationSchedule {
    private final QuotationService quotationService;

    public QuotationSchedule(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @Transactional
    @Scheduled(every = "60s", identity = "task-job")
    public void schedule() {
        quotationService.getCurrencyPrice();
    }
}
