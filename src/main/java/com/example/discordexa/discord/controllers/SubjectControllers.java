//package com.example.discordexa.discord.controllers;//package controllers;
//
//
//import com.example.discordexa.discord.DTO.ChannelGetDTO;
//import com.example.discordexa.discord.bean.Channel;
//import com.example.discordexa.discord.bean.Subject;
//import com.example.discordexa.discord.repository.ChannelRepository;
//import com.example.discordexa.discord.repository.SubjectRepository;
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
//public class SubjectControllers {
//
//    @Autowired
//    SubjectRepository subjectRepository;
//
//
//    @GetMapping("/channels")
//    public List<Subject> getSubject() {
//        return subjectRepository.findAll();
//    }
//
//
//    @GetMapping("/subject/{id}")
//    public ResponseEntity<Subject> getSubjectByID(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
//
//        Optional<Subject> optionalSubject = subjectRepository.findById(id);
//
//        if(optionalSubject.isPresent()) {
//        return new ResponseEntity<>(optionalSubject.get(), HttpStatus.OK);
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
//    @DeleteMapping("/subject/{id}")
//    public ResponseEntity<Subject> deleteSubject(@PathVariable("id") Integer id) {
//        Optional<Subject> subjectOptional = subjectRepository.findById(id);
//
//        if(subjectOptional.isPresent()){
//            subjectRepository.deleteById(id);
//            return new ResponseEntity<>(null,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
//}
