//package com.example.discordexa.discord.controllers;//package controllers;
//
//
//import com.example.discordexa.discord.DTO.ChannelGetDTO;
//import com.example.discordexa.discord.DTO.MessageGetDTO;
//import com.example.discordexa.discord.bean.Channel;
//import com.example.discordexa.discord.bean.Message;
//import com.example.discordexa.discord.repository.ChannelRepository;
//import com.example.discordexa.discord.repository.MessageRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//
//@RestController
//@CrossOrigin
//public class MessageControllers {
//
//    @Autowired
//    MessageRepository messageRepository;
//
//
//    @GetMapping("/messages")
//    public List<Message> getChannel() {
//        return messageRepository.findAll();
//    }
//
//
//    @GetMapping("/message/{id}")
//    public ResponseEntity<MessageGetDTO> getGroupById(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
//
//        Optional<Message> optionalMessage = messageRepository.findById(id);
//
//        if(optionalMessage.isPresent()) {
//            ModelMapper mapper = new ModelMapper();
//            MessageGetDTO messageGetDTO = mapper.map(optionalMessage.get(), MessageGetDTO.class);
//            return new ResponseEntity<>(messageGetDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//////    @POST
//////    @Path("/channels")
//////    @Produces(MediaType.APPLICATION_JSON)
//////    public Response addGroup(ChannelCreateDTO channelCreateDTO) throws SQLException, ClassNotFoundException {
//////
//////        ModelMapper mapper = new ModelMapper();
//////
//////
//////        Channel channel = new Channel(
//////                channelCreateDTO.getName().trim()
//////        );
//////        Channel channel1 = repositoryChannel.addChannel(channel);
//////        ChannelGetDTO channelGetDTO = mapper.map(channel1, ChannelGetDTO.class);
//////
//////        return Response.ok(channelGetDTO)
//////                .status(201)
//////                .build();
//////    }
//
//
//
//    @DeleteMapping("/message/{id}")
//    public ResponseEntity<Message> deleteChannel(@PathVariable("id") Integer id) {
//        Optional<Message> optionalMessage = messageRepository.findById(id);
//
//        if(optionalMessage.isPresent()){
//            messageRepository.deleteById(id);
//            return new ResponseEntity<>(null,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
//}
