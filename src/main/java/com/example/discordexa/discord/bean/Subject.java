package com.example.discordexa.discord.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Subject {
    @Column(name = "sub_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "sub_sent_at")
    protected LocalDateTime sentAt;




    public long getId() {
        return id;
    }
}
