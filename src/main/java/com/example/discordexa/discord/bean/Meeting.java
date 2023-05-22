package com.example.discordexa.discord.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "meeting")
public class Meeting extends Subject{
    @Column(name = "mee_name")
    private String name;

    @Column(name = "mee_datetime")
    private LocalDateTime datetime;

    @Column(name = "mee_duration")
    private int duration;


    public Meeting() {

    }
}
