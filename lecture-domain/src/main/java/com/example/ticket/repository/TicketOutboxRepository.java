package com.example.ticket.repository;

import com.example.ticket.domain.TicketOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketOutboxRepository extends JpaRepository<TicketOutbox, Long> {
}
