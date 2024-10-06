package com.example.ticket.manager;

import com.example.ticket.domain.Ticket;
import com.example.ticket.dto.TicketRequest;
import com.example.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class TickerCommandManager {

    private final TicketRepository ticketRepository;

    public Ticket createTicket(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket(
                ticketRequest.amount(),
                ticketRequest.lectureId()
        );
        return ticketRepository.save(ticket);
    }
}
