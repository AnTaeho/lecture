package com.example.producer;

import com.example.event.ticket.TicketEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TickerMessageProducer {

    private final KafkaTemplate<String, TicketEvent> kafkaTemplate;

    public void create(TicketEvent ticketEvent) {
        kafkaTemplate.send("issue-ticket", ticketEvent);
    }
}
