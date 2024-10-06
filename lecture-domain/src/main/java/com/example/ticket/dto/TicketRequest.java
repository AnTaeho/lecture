package com.example.ticket.dto;

public record TicketRequest(
        Long lectureId,
        int amount
) {
}
