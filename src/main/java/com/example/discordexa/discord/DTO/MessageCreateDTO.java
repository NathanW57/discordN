package com.example.discordexa.discord.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageCreateDTO {
        private long userId;
        private String content;
        private int sender;



    public boolean isValidContent() {
        return content != null && !content.trim().isEmpty();
    }

    public boolean isValidSender() {
        return sender > 0;
    }


}
