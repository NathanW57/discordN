package com.example.discordexa.discord.bean;

import com.example.discordexa.discord.DTO.UserGetDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutSocketMessage {

     UserGetDTO user;
     String content;
     String time;

    public OutSocketMessage() {
    }

    public OutSocketMessage(UserGetDTO user, String content, String time) {
        this.user = user;
        this.content = content;
        this.time = time;
    }



}
