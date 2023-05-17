package com.example.discordexa.discord.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Meeting {

    @Id
    @Column(name = "sub_id")
    private long id;

    @Column(name = "mee_name")
    private String name;

    @Column(name = "mee_datetime")
    private LocalDateTime datetime;

    @Column(name = "mee_duration")
    private int duration;

//    @Column(name = "mee_user")
//    @OneToOne
//    private User organizer;

    public Meeting(LocalDateTime datetime, int duration) {
        this.datetime = datetime;
        this.duration = duration;
    }

    public Meeting() {

    }
}
