package org.br.matheuscordeiro.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Jacksonized
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OpportunityDto(String proposalId, String customer, BigDecimal priceTonne, BigDecimal lastCurrencyQuotation) {
}
