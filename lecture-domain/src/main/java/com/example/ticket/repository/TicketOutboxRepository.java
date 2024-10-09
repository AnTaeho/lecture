package com.example.ticket.repository;

import com.example.ticket.domain.TicketOutbox;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketOutboxRepository extends JpaRepository<TicketOutbox, Long> {

    @Query("select tob from TicketOutbox tob where tob.ticketId = :ticketId and tob.email = :email")
    Optional<TicketOutbox> findByTicketIdAndEmail(
            @Param("ticketId") Long ticketId,
            @Param("email") String email
    );
}
