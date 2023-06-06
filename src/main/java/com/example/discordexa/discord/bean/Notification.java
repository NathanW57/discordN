package com.example.discordexa.discord.bean;


import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "not_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "not_receiver")
    private User receiver;

    @Column(name = "not_seen_at")
    private LocalDateTime seenAt;



    public Notification() {

    }
}
