package com.example.consumer;

import com.example.event.TicketEvent;
import com.example.ticket.domain.TicketOutbox;
import com.example.ticket.domain.UserTicket;
import com.example.ticket.repository.TicketOutboxRepository;
import com.example.ticket.repository.UserTicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CouponCreateConsumer {

    private final UserTicketRepository userTicketRepository;
    private final TicketOutboxRepository ticketOutboxRepository;

    @KafkaListener(topics = "issue-ticket", groupId = "group_1")
    public void listener(TicketEvent ticketEvent) {
        log.info("ticketId = {} email = {}", ticketEvent.ticketId(), ticketEvent.email());
        userTicketRepository.save(new UserTicket(ticketEvent.ticketId(), ticketEvent.email()));
        TicketOutbox ticketOutbox = ticketOutboxRepository.findByTicketIdAndEmail(ticketEvent.ticketId(), ticketEvent.email())
                .orElseThrow(() -> new IllegalArgumentException("해당 기록을 찾을 수 없습니다."));
        ticketOutbox.done();
    }

}
