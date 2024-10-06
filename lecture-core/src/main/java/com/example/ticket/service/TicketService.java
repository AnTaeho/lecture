package com.example.ticket.service;

import com.example.event.ticket.TicketEvent;
import com.example.redis.RedisTicketService;
import com.example.ticket.dto.TicketRequest;
import com.example.ticket.dto.TicketResponse;
import com.example.ticket.manager.TickerCommandManager;
import com.example.ticket.manager.TicketQueryManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketQueryManager ticketQueryManager;
    private final TickerCommandManager tickerCommandManager;
    private final RedisTicketService redisTicketService;
    private final ApplicationEventPublisher publisher;

    public TicketResponse createTicket(TicketRequest ticketRequest) {
        return new TicketResponse(tickerCommandManager.createTicket(ticketRequest).getId());
    }

    public void issueTicket(Long ticketId, String email) {
        Long add = redisTicketService.addKeyToSet(ticketId + ":" + email);
        if (add != 1L) {
            return;
        }

        int amount = ticketQueryManager.getAmount(ticketId);
        Long increment = redisTicketService.increment();
        if (amount < increment) {
            return;
        }

        publisher.publishEvent(new TicketEvent(ticketId, email));
    }


}
