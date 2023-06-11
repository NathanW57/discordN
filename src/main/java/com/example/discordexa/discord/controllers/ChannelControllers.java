package com.example.discordexa.discord.controllers;

import com.example.discordexa.discord.DTO.ChannelGetDTO;
import com.example.discordexa.discord.DTO.ChannelGetFinestDTO;
import com.example.discordexa.discord.DTO.UserGetDTO;
import com.example.discordexa.discord.bean.Channel;
import com.example.discordexa.discord.mapper.ChannelMapper;
import com.example.discordexa.discord.repository.ChannelRepository;
import com.example.discordexa.discord.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
public class ChannelControllers {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/channels")
    public ResponseEntity<List<ChannelGetDTO>> getChannel() {
        List<ChannelGetDTO> channelList = channelRepository.findAll()
                .stream()
                .map(ChannelMapper::toGetDto)
                .toList();
        return new ResponseEntity<>(channelList, HttpStatus.OK);
    }


    @GetMapping("/channel/{id}")
    public ResponseEntity<ChannelGetDTO> getGroupById(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {

        return channelRepository.findById(id).map(ChannelMapper::toGetDto)
                .map(channelGetDTO -> new ResponseEntity<>(channelGetDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    //getGroupFinestById
    @GetMapping("/channel/finest/{id}")
    public ResponseEntity<ChannelGetFinestDTO> getChannelFinestById(@PathVariable("id") Long id) throws SQLException, ClassNotFoundException {

        Optional<Channel> optionalChannel = channelRepository.findById(Math.toIntExact(id));

        if(optionalChannel.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            ChannelGetFinestDTO channelGetDTO = mapper.map(optionalChannel.get(), ChannelGetFinestDTO.class);

            List<UserGetDTO> userDTOs = channelGetDTO.getMembers()
                    .stream()
                    .map(user -> {
                        UserGetDTO userDTO = new UserGetDTO();
                        userDTO.setId(user.getId());
                        userDTO.setFirstname(user.getFirstname());
                        userDTO.setLastname(user.getLastname());
                        userDTO.setEmail(user.getEmail());
                        return userDTO;
                    })
                    .collect(Collectors.toList());

            List<UserGetDTO> userSubscribes = channelGetDTO.getSubscribers()
                    .stream()
                    .map(user -> {
                        UserGetDTO userDTO = new UserGetDTO();
                        userDTO.setId(user.getId());
                        userDTO.setFirstname(user.getFirstname());
                        userDTO.setLastname(user.getLastname());
                        userDTO.setEmail(user.getEmail());
                        return userDTO;
                    })
                    .collect(Collectors.toList());

                    channelGetDTO.setSubscribers(userSubscribes);
                    channelGetDTO.setMembers(userDTOs);

            return new ResponseEntity<>(channelGetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


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
