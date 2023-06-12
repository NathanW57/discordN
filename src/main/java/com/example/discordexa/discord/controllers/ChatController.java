package com.example.discordexa.discord.controllers;

import com.example.discordexa.discord.bean.Message;
import com.example.discordexa.discord.bean.User;
import jakarta.transaction.Transactional;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController {

    @MessageMapping("/send/Message")
    @SendTo("/topic/messages")
    @Transactional
    public Message sendMessage(Message message) {
        User sender = message.getSender();
        String content = message.getContent();
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new Message(sender, content, time);
    }
}

