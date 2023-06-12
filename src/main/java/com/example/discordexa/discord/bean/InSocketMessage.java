package com.example.discordexa.discord.bean;


import lombok.Getter;
import lombok.Setter;

public class InSocketMessage {

    private Long userId;
    private Long channelId;
    private String content;

    public InSocketMessage() {
    }

    public InSocketMessage(Long userId, Long channelId, String content) {
        this.userId = userId;
        this.channelId = channelId;
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
