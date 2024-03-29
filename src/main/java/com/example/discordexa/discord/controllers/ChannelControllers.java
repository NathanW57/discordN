package com.example.discordexa.discord.controllers;

import com.example.discordexa.discord.DTO.*;
import com.example.discordexa.discord.Enum.EVisibility;
import com.example.discordexa.discord.bean.Channel;
import com.example.discordexa.discord.bean.Group;
import com.example.discordexa.discord.mapper.ChannelMapper;
import com.example.discordexa.discord.mapper.GroupMapper;
import com.example.discordexa.discord.repository.ChannelRepository;
import com.example.discordexa.discord.repository.UserRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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


    @GetMapping("/user/{userId}/channels")
    public ResponseEntity<List<ChannelGetDTO>> getUserChannels(@PathVariable("userId") Long userId) {
        List<Channel> userChannels = channelRepository.findAllByMembers_Id(userId);
        List<ChannelGetDTO> channelGetDTOs = userChannels.stream()
                .map(ChannelMapper::toGetDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(channelGetDTOs, HttpStatus.OK);
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

    @GetMapping("/channel/{id}/members")
public ResponseEntity<List<UserGetDTO>> getMembers(@PathVariable("id") Long id) {
        Optional<Channel> optionalChannel = channelRepository.getChannelByIAndMembers(id);

        if(optionalChannel.isPresent()){
            List<UserGetDTO> userDTOs = optionalChannel.get().getMembers()
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
            return new ResponseEntity<>(userDTOs,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(value="/channel")
    public ResponseEntity<ChannelGetDTO> addChannel(@Valid @RequestBody ChannelCreateDTO channelCreateDTO)  {
        try
        {
            Channel channel = ChannelMapper.toEntity(channelCreateDTO);


            channel.setVisibility(EVisibility.PUBLIC);
            Channel savedChannel = channelRepository.save(channel);


            return new ResponseEntity<>(ChannelMapper.toGetDto(savedChannel),HttpStatus.CREATED);
        }
        catch (DataAccessException dae) {
            throw new RuntimeException(dae.getMessage());
        }
    }
}
