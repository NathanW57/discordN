package com.example.discordexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
public class DiscordExaApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(DiscordExaApplication.class, args);
    }

}
