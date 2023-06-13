//package com.example.discordexa.discord.controllers;
//
//import com.example.discordexa.discord.bean.*;
//import com.example.discordexa.discord.mapper.UserMapper;
//import com.example.discordexa.discord.repository.ChannelRepository;
//import com.example.discordexa.discord.repository.UserRepository;
//import jakarta.transaction.Transactional;
//import jakarta.ws.rs.NotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Controller
//public class ChatController {
//
//    @Autowired
//    ChannelRepository channelRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @MessageMapping("/chat/{channelId}")
//    @SendTo("/topic/messages/{channelId}")
//    @Transactional
//    public OutSocketMessage send(@DestinationVariable Long channelId, @RequestBody InSocketMessage inSocketMessage) {
//        Channel channel = channelRepository.findById(Math.toIntExact(channelId))
//                .orElseThrow(() -> new NotFoundException("Could not find channel with id=" + channelId));
//
//        User user = userRepository.findById(Math.toIntExact(inSocketMessage.getUserId()))
//                .orElseThrow(() -> new NotFoundException("Could not find user with id=" + inSocketMessage.getUserId()));
//
//        String time = new SimpleDateFormat("HH:mm").format(new Date());
//        Message message = new Message(user, inSocketMessage.getContent(), time);
//        channel.addMessage(message);
//
//        try {
//            channelRepository.save(channel);
//        } catch (DataAccessException e) {
//            throw new InternalError("Could not save message");
//        }
//
//        return new OutSocketMessage(UserMapper.toGetDto(user), inSocketMessage.getContent(), time);
//    }
//}
