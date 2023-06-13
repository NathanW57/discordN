package com.example.discordexa.discord.exception;

import jakarta.ws.rs.NotFoundException;

public class UserException extends IllegalArgumentException {
    public UserException(String message) {
        super(message);
    }
}
