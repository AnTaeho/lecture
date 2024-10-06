package com.example.user.dto;

public record JoinRequest (
        String username,
        String email,
        String password
) {
}
