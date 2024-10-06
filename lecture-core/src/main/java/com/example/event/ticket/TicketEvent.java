package com.example.event.ticket;

public record TicketEvent (
        Long ticketId,
        String email
) {
}
