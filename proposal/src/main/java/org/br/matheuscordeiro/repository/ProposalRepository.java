package org.br.matheuscordeiro.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.br.matheuscordeiro.entity.Proposal;

import java.util.Optional;

@ApplicationScoped
public class ProposalRepository implements PanacheRepository<Proposal> {
    public Optional<Proposal> findByCustomer(String customer) {
        return Optional.of(find("customer", customer).firstResult());
    }
}
