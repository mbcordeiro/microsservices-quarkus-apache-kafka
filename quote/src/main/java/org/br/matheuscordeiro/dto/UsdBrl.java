package org.br.matheuscordeiro.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UsdBrl(String code, String codeIn, String name, String high, String low, String varBid,
                     String percentageChange, String bid, String ask, String timestamp, String createDate) {
}
