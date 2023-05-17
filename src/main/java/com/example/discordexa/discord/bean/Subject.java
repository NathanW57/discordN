package com.example.discordexa.discord.bean;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Subject {
    @Column(name = "sub_id")
    protected Long id;

    @Column(name = "sub_sent_at")
    protected LocalDateTime sentAt;

    @Column(name = "sub_channel")
    protected Channel channel;

    public Long getId() {
        return id;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public Channel getChannel() {
        return channel;
    }
}
