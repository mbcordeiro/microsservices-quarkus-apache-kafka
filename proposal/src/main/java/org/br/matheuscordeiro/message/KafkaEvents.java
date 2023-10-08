package org.br.matheuscordeiro.message;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.br.matheuscordeiro.dto.ProposalDto;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
@Slf4j
public class KafkaEvents {
    @Channel("proposal")
    Emitter<ProposalDto> proposalRequestEmitter;

    public void sendNewKafkaEvents(ProposalDto proposalDto) {
        log.info("Send new proposal to kafka topic");
        proposalRequestEmitter.send(proposalDto).toCompletableFuture().join();
    }
}
