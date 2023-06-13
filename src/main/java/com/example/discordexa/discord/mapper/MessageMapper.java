package com.example.discordexa.discord.mapper;


import com.example.discordexa.discord.DTO.MessageCreateDTO;
import com.example.discordexa.discord.DTO.MessageGetDTO;
import com.example.discordexa.discord.bean.Message;

public class MessageMapper {

    public static Message toEntity(MessageCreateDTO messageCreateDTO) {
        Message message = new Message();
        message.setContent(message.getContent());
        message.setFile(message.getFile());
        message.setSentAt(message.getSentAt());
        return message;
    }

    public static MessageGetDTO toGetDto(Message message) {
        MessageGetDTO messageGetDTO = new MessageGetDTO();
        message.setId(messageGetDTO.getId());
        messageGetDTO.getSender();
        messageGetDTO.getFilePath();
        messageGetDTO.getFileName();
        message.setContent(messageGetDTO.getContent());
        return messageGetDTO;
    }
}
