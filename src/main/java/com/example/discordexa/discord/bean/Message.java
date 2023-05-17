//package com.example.discordexa.discord.bean;//package bean;
////
//
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//
//@Entity
//@Getter
//@Setter
//public class Message {
//
//    @Id
//    @Column(name = "sub_id")
//    private long id;
//    @Column(name = "msg_content")
//    private String content;
//
//    @ManyToOne
//    @Column(name = "msg_sender")
//    private User sender;
////    private Channel channel;
//
////    @Column(name = "msg_file")
////    private File file;
//
//    public Message(String content, User sender) {
//        this.content = content;
//        this.sender = sender;
//    }
//
//    public Message() {
//
//    }
//
//}
