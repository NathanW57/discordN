package com.example.discordexa.discord.DTO;

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


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
