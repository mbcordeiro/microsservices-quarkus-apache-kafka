package org.br.matheuscordeiro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime date;

    @Column(name = "proposal_id")
    private Long proposalId;

    private String customer;

    private BigDecimal priceTonne;

    @Column(name = "last_currency_quotation")
    private BigDecimal lastCurrencyQuotation;
}
