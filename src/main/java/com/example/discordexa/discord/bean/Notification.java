package com.example.discordexa.discord.bean;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.security.auth.Subject;


@Entity
@Getter
@Setter
public class Notification {

    @Id
    private long id;
    private boolean seen;

    public Notification(Boolean notSeen) {
    }

    public Notification() {

    }


    public long getId() {
        return id;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }


    public Subject getSubject(){
        return null;
    }
}
