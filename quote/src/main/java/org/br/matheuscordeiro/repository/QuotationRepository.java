package org.br.matheuscordeiro.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.br.matheuscordeiro.entity.Quotation;

@ApplicationScoped
public class QuotationRepository implements PanacheRepository<Quotation> {
}
