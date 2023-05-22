package com.example.discordexa.discord.controllers;//package controllers;



import com.example.discordexa.discord.DTO.MessageCreateDTO;
import com.example.discordexa.discord.DTO.MessageGetDTO;
import com.example.discordexa.discord.DTO.UserCreateDTO;
import com.example.discordexa.discord.DTO.UserGetDTO;
import com.example.discordexa.discord.bean.Message;
import com.example.discordexa.discord.bean.User;
import com.example.discordexa.discord.repository.MessageRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
public class MessageControllers {


    @Autowired
    MessageRepository messageRepository;



    @GetMapping("/messages")
    public ResponseEntity<List<MessageGetDTO>> getAllMessage() throws SQLException, ClassNotFoundException {
        List<Message> messageList = messageRepository.findAll();

        ModelMapper mapper = new ModelMapper();

        return new ResponseEntity<>(messageList
                .stream()
                .map((message) -> mapper.map(message, MessageGetDTO.class))
                .toList(), HttpStatus.OK);
    }


    @GetMapping("/messages/{idSender}")
    public ResponseEntity<List<MessageGetDTO>> getAllMessageBySenderID(@PathVariable("idSender") Integer id) throws SQLException, ClassNotFoundException {
        List<Message> messageList = messageRepository.getAllBySenderId(id);

        ModelMapper mapper = new ModelMapper();

        return new ResponseEntity<>(messageList
                .stream()
                .map((message) -> mapper.map(message, MessageGetDTO.class))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<MessageGetDTO> getMessageById(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {

        Optional<Message> optionalMessage = messageRepository.findById(id);

        if(optionalMessage.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            MessageGetDTO messageGetDTO = mapper.map(optionalMessage.get(), MessageGetDTO.class);
            return new ResponseEntity<>(messageGetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PostMapping("/message")
    public ResponseEntity<Message> addMessage(@Valid MessageCreateDTO messageCreateDTO,@RequestPart("message") Message message) throws SQLException, ClassNotFoundException {

        ModelMapper mapper = new ModelMapper();

        message = mapper.map(messageCreateDTO, Message.class);
        Message newMessage = messageRepository.save(message);

        MessageGetDTO messageGetDTO = mapper.map(newMessage, MessageGetDTO.class);
        return new ResponseEntity<>(message,HttpStatus.CREATED);

    }



    @DeleteMapping("/message/{id}")
    public ResponseEntity<Message> deleteChannel(@PathVariable("id") Integer id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);

        if(optionalMessage.isPresent()){
            messageRepository.deleteById(id);
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
