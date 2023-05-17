package com.example.discordexa.discord.controllers;//package controllers;


import com.example.discordexa.discord.DTO.ChannelGetDTO;
import com.example.discordexa.discord.bean.Channel;
import com.example.discordexa.discord.bean.Notification;
import com.example.discordexa.discord.repository.ChannelRepository;
import com.example.discordexa.discord.repository.NotificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
public class NotificationControllers {

    @Autowired
    NotificationRepository notificationRepository;


    @GetMapping("/notifications")
    public List<Notification> getChannel() {
        return notificationRepository.findAll();
    }


    @GetMapping("/notification/{id}")
    public ResponseEntity<Notification> getGroupById(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {

        Optional<Notification> optionalNotification = notificationRepository.findById(id);

        if(optionalNotification.isPresent()) {
      return new ResponseEntity<>(optionalNotification.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

////    @POST
////    @Path("/channels")
////    @Produces(MediaType.APPLICATION_JSON)
////    public Response addGroup(ChannelCreateDTO channelCreateDTO) throws SQLException, ClassNotFoundException {
////
////        ModelMapper mapper = new ModelMapper();
////
////
////        Channel channel = new Channel(
////                channelCreateDTO.getName().trim()
////        );
////        Channel channel1 = repositoryChannel.addChannel(channel);
////        ChannelGetDTO channelGetDTO = mapper.map(channel1, ChannelGetDTO.class);
////
////        return Response.ok(channelGetDTO)
////                .status(201)
////                .build();
////    }



    @DeleteMapping("/notification/{id}")
    public ResponseEntity<Notification> deleteChannel(@PathVariable("id") Integer id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);

        if(optionalNotification.isPresent()){
            notificationRepository.deleteById(id);
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
