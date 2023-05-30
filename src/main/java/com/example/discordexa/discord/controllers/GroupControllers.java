package com.example.discordexa.discord.controllers;//package controllers;

import com.example.discordexa.discord.DTO.GroupCreateDTO;
import com.example.discordexa.discord.DTO.GroupGetDTO;
import com.example.discordexa.discord.DTO.GroupGetFinestDTO;
import com.example.discordexa.discord.DTO.UserGetDTO;
import com.example.discordexa.discord.bean.Group;
import com.example.discordexa.discord.bean.User;
import com.example.discordexa.discord.repository.GroupRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class GroupControllers {

    @Autowired
    private GroupRepository groupRepository;

    private static final Logger logger = LoggerFactory.getLogger(GroupControllers.class);




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




    @GetMapping("/group/{id}")
    public ResponseEntity<GroupGetFinestDTO> getGroupByID(@PathVariable("id") Long id) {
        logger.info("Received request for group with id {}", id);

        Optional<Group> optionalGroup = groupRepository.findById(Math.toIntExact(id));
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            GroupGetFinestDTO groupDTO = new GroupGetFinestDTO();
            groupDTO.setId(group.getId());
            groupDTO.setName(group.getName());

            List<UserGetDTO> userDTOs = group.getMembers()
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

            groupDTO.setMembers(userDTOs);

            logger.info("Responding with group {}", groupDTO);  // Log the returned group

            return ResponseEntity.ok().body(groupDTO);
        } else {
            logger.error("Could not find group with id {}", id);  // Log error when group not found
            throw new NotFoundException("Could not find group with id=" + id);
        }
    }







    @GetMapping("/groupsDetail")
    public ResponseEntity<List<GroupGetFinestDTO>> getAllGroupWithMembers(){
        List<Group> groupList = groupRepository.findAll();
        System.out.println(groupList.stream().toList());
        ModelMapper mapper = new ModelMapper();
        return new ResponseEntity<>(groupList
                .stream()
                .map((group) -> mapper.map(group, GroupGetFinestDTO.class)).peek(System.out::println)
                .toList(),(HttpStatus.OK));
    }


//    @GetMapping("/group/{id}")
//    public ResponseEntity<GroupGetDTO> getGroup(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
//        Optional<Group> optionalGroup = groupRepository.findById(id);
//
//        if(optionalGroup.isPresent()) {
//            ModelMapper mapper = new ModelMapper();
//            GroupGetDTO groupGetDTO = mapper.map(optionalGroup.get(), GroupGetDTO.class);
//            return new ResponseEntity<>(groupGetDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


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



