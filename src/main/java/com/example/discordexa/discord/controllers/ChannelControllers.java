package com.example.discordexa.discord.controllers;

import com.example.discordexa.discord.DTO.ChannelGetDTO;
import com.example.discordexa.discord.bean.Channel;
import com.example.discordexa.discord.repository.ChannelRepository;
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
public class ChannelControllers {

    @Autowired
    ChannelRepository channelRepository;


    @GetMapping("/channels")
    public ResponseEntity<List<ChannelGetDTO>> getChannel() {
        List<Channel> channelList = channelRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return new ResponseEntity<>(channelList
                .stream()
                .map((channel) -> mapper.map(channel, ChannelGetDTO.class))
                .toList(), HttpStatus.OK);
    }


    @GetMapping("/channel/{id}")
    public ResponseEntity<ChannelGetDTO> getGroupById(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {

        Optional<Channel> optionalChannel = channelRepository.findById(id);

        if(optionalChannel.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            ChannelGetDTO channelGetDTO = mapper.map(optionalChannel.get(), ChannelGetDTO.class);
            return new ResponseEntity<>(channelGetDTO, HttpStatus.OK);
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



    @DeleteMapping("/channel/{id}")
    public ResponseEntity<Channel> deleteChannel(@PathVariable("id") Integer id) {
        Optional<Channel> optionalChannel = channelRepository.findById(id);

        if(optionalChannel.isPresent()){
            channelRepository.deleteById(id);
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
