package com.example.discordexa.discord.bean;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import javax.security.auth.Subject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Getter
@Setter
@Table(name = "notification")
public class Notification {

    @Id
    @Column(name = "not_id")
    private long id;

    @Column(name = "not_seen")
    private boolean seen;

    @Column(name = "not_seen_at")
    private LocalDateTime seenAt;


    public Notification() {

    }
}
