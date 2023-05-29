package com.example.discordexa.discord.bean;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "message")
public class Message extends Subject{

    @Column(name = "msg_content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "msg_sender")
    private User sender;

    @JoinColumn(name = "msg_file")
    @OneToOne
    private File file;


    public Message() {

    }
}
