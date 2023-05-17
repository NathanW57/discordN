package com.example.discordexa.discord.controllers;//package controllers;

import com.example.discordexa.discord.DTO.GroupCreateDTO;
import com.example.discordexa.discord.DTO.GroupGetDTO;
import com.example.discordexa.discord.DTO.GroupGetFinestDTO;
import com.example.discordexa.discord.DTO.UserGetDTO;
import com.example.discordexa.discord.bean.Group;
import com.example.discordexa.discord.bean.User;
import com.example.discordexa.discord.repository.GroupRepository;
import jakarta.validation.Valid;
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
public class GroupControllers {

    @Autowired
    private GroupRepository groupRepository;




    /**
     * Retrieves all groups.
     *
     * @return a list of groups
     */
    @GetMapping("/groups")
    public ResponseEntity<List<GroupGetDTO>> getAllGroup() throws SQLException, ClassNotFoundException {
        List<Group> groupList = groupRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return new ResponseEntity<>(groupList
                .stream()
                .map((group) -> mapper.map(group, GroupGetDTO.class))
                .toList(), HttpStatus.OK);
    }


    @GetMapping("/groupsDetail")
    public ResponseEntity<List<GroupGetFinestDTO>> getAllGroupWithMembers(){
        List<Group> groupList = groupRepository.getGroupAll();
        ModelMapper mapper = new ModelMapper();
        return new ResponseEntity<>(groupList
                .stream()
                .map((group) -> mapper.map(group, GroupGetFinestDTO.class))
                .toList(),(HttpStatus.OK));
    }


    @GetMapping("/group/{id}")
    public ResponseEntity<GroupGetDTO> getGroup(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
        Optional<Group> optionalGroup = groupRepository.findById(id);

        if(optionalGroup.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            GroupGetDTO groupGetDTO = mapper.map(optionalGroup.get(), GroupGetDTO.class);
            return new ResponseEntity<>(groupGetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/groups")
    public ResponseEntity<GroupGetDTO> addGroup(@Valid GroupCreateDTO groupCreateDTO) throws SQLException, ClassNotFoundException {
        ModelMapper mapper = new ModelMapper();
        Group group = mapper.map(groupCreateDTO,Group.class);
        Group newGroup = groupRepository.save(group);

        GroupGetDTO groupGetDTO = mapper.map(newGroup, GroupGetDTO.class);

        return new ResponseEntity<>(groupGetDTO,HttpStatus.CREATED);
    }


//    @PUT
//    @Path("/groups")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateGroup(Group group) {
//        try {
//            Group updatedGroup = groupRepository.updateGroup(group);
//            return Response.ok(updatedGroup).build();
//        } catch (NotFoundException e) {
//            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
//        } catch (SQLException e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
//        }
//    }


    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable("id") Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);

        if(optionalGroup.isPresent()){
            groupRepository.deleteById(id);
            return new ResponseEntity<>(optionalGroup.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    }



