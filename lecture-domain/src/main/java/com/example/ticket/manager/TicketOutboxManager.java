package com.example.ticket.manager;

import com.example.ticket.domain.TicketOutbox;
import com.example.ticket.repository.TicketOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketOutboxManager {

    private final TicketOutboxRepository ticketOutboxRepository;

    public void save(TicketOutbox ticketOutbox) {
        ticketOutboxRepository.save(ticketOutbox);
    }

}
