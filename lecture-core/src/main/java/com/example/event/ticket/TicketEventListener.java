package com.example.event.ticket;

import com.example.producer.TickerMessageProducer;
import com.example.ticket.domain.TicketOutbox;
import com.example.ticket.manager.TicketOutboxManager;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class TicketEventListener {

    private final TicketOutboxManager ticketOutboxManager;
    private final TickerMessageProducer tickerMessageProducer;

    @TransactionalEventListener(value = TicketEvent.class, phase = TransactionPhase.BEFORE_COMMIT)
    public void saveOutboxInfo(TicketEvent ticketEvent) {
        ticketOutboxManager.save(new TicketOutbox(ticketEvent.ticketId(), ticketEvent.email()));
    }

    @Async
    @TransactionalEventListener(value = TicketEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void produceMessage(TicketEvent ticketEvent) {
        tickerMessageProducer.create(ticketEvent);
    }

}
