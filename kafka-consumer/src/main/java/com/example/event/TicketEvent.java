package com.example.event;

public record TicketEvent(
        Long ticketId,
        String email
) {
}
