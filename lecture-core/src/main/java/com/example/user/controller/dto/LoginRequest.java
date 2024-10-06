package com.example.user.controller.dto;

public record LoginRequest (
        String email,
        String password
) {
}
