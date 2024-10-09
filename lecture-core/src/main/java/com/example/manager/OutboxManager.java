package com.example.manager;

import com.example.event.ticket.TicketEvent;
import com.example.producer.TickerMessageProducer;
import com.example.ticket.domain.TicketOutbox;
import com.example.ticket.manager.TicketOutboxManager;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxManager {

    private final TicketOutboxManager ticketOutboxManager;
    private final TickerMessageProducer tickerMessageProducer;

    @Scheduled(fixedRate = 300000)
    public void retryFailedMessage() {
        List<TicketOutbox> allPublished = ticketOutboxManager.findAllPublished();
        for (TicketOutbox ticketOutbox : allPublished) {
            tickerMessageProducer.create(
                    new TicketEvent(ticketOutbox.getTicketId(), ticketOutbox.getEmail())
            );
        }
    }

    @Scheduled(fixedRate = 3600000)
    public void deleteOldMessage() {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        List<Long> outboxOneHourAgo = ticketOutboxManager.findOutboxOneHourAgo(oneHourAgo);
        ticketOutboxManager.deleteAllIn(outboxOneHourAgo);
    }
}
