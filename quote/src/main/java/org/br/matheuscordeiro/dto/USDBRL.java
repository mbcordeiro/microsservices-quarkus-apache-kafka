package org.br.matheuscordeiro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record USDBRL(String code, String codein, String name, String high, String low, String varBid,
                     String pctChange, String bid, String ask, String timestamp,
                     @JsonProperty("create_date") String createDate) {
}
