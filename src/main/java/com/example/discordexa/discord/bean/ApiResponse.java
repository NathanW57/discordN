package com.example.discordexa.discord.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private boolean success;
    private String message;
    private User user;

    // getters and setters
}

