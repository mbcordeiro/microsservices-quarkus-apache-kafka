package org.br.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.br.matheuscordeiro.client.CurrencyPriceClient;
import org.br.matheuscordeiro.dto.CurrencyPriceDto;
import org.br.matheuscordeiro.dto.QuotationDto;
import org.br.matheuscordeiro.entity.Quotation;
import org.br.matheuscordeiro.message.KafkaEvents;
import org.br.matheuscordeiro.repository.QuotationRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApplicationScoped
public class QuotationService {
    public static final String USD_BRL = "USD-BRL";

    @Inject
    @RestClient
    private final CurrencyPriceClient currencyPriceClient;
    private final QuotationRepository quotationRepository;
    private final KafkaEvents kafkaEvents;

    public QuotationService(CurrencyPriceClient currencyPriceClient, QuotationRepository quotationRepository, KafkaEvents kafkaEvents) {
        this.currencyPriceClient = currencyPriceClient;
        this.quotationRepository = quotationRepository;
        this.kafkaEvents = kafkaEvents;
    }

    public void getCurrencyPrice() {
        final var currencyPriceDto = currencyPriceClient.getPriceByPair(USD_BRL);
        if (updateCurrencyInfoPrice(currencyPriceDto)) {
            kafkaEvents.sendNewKafkaEvent(QuotationDto.builder()
                    .currencyPrice(new BigDecimal(currencyPriceDto.usdbrl().bid())).date(LocalDateTime.now()).build());
        }
    }

    private boolean updateCurrencyInfoPrice(CurrencyPriceDto currencyPriceInfo) {
        final var currentPrice = new BigDecimal(currencyPriceInfo.usdbrl().bid());
        var updatePrice = false;
        final var quotationList = quotationRepository.findAll().list();
        if (quotationList.isEmpty()) {
            saveQuotation(currencyPriceInfo);
            updatePrice = true;
        } else {
            final var lastDollarPrice = quotationList.get(quotationList.size() - 1);
            if (currentPrice.floatValue() > lastDollarPrice.getCurrencyPrice().floatValue()) {
                updatePrice = true;
                saveQuotation(currencyPriceInfo);
            }
        }
        return updatePrice;
    }

    private void saveQuotation(CurrencyPriceDto currencyPriceInfo) {
        final var quotation = Quotation.builder().date(LocalDateTime.now())
                .currencyPrice(new BigDecimal(currencyPriceInfo.usdbrl().bid()))
                .percentageChange(currencyPriceInfo.usdbrl().pctChange())
                .pair(USD_BRL).build();
        quotationRepository.persist(quotation);
    }
}
