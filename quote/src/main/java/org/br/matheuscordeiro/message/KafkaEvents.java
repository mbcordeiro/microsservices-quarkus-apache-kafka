package org.br.matheuscordeiro.message;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.br.matheuscordeiro.dto.QuotationDto;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
@Slf4j
public class KafkaEvents {
    @Channel("quotation-channel")
    Emitter<QuotationDto> quotationRequestEmitter;

    public void sendNewKafkaEvent(QuotationDto quotationDto) {
        log.info("Send quotation to kafka topic");
        quotationRequestEmitter.send(quotationDto).toCompletableFuture().join();
    }
}
