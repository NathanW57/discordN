package com.example.discordexa.discord.controllers;

import com.example.discordexa.discord.DTO.GroupCreateDTO;
import com.example.discordexa.discord.DTO.GroupGetDTO;
import com.example.discordexa.discord.DTO.GroupGetFinestDTO;
import com.example.discordexa.discord.DTO.UserGetDTO;
import com.example.discordexa.discord.bean.Group;
import com.example.discordexa.discord.bean.User;
import com.example.discordexa.discord.mapper.GroupMapper;
import com.example.discordexa.discord.mapper.UserMapper;
import com.example.discordexa.discord.repository.GroupRepository;
import com.example.discordexa.discord.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(GroupControllers.class);




    @GetMapping("/groups")
    public ResponseEntity<List<GroupGetDTO>> getAllGroup() throws SQLException, ClassNotFoundException {
        List<GroupGetDTO> groupList = groupRepository.findAll()
                .stream()
                .map(GroupMapper::toGetDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }



    @DeleteMapping("/group/{groupId:[0-9]+}/members/{userId:[0-9]+}")
    public ResponseEntity<?> removeMember(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId) {
        try {
            Group group = groupRepository.findById(Math.toIntExact(groupId))
                    .orElseThrow(() -> new NotFoundException("Could not find group with id=" + groupId));

            User user = userRepository.findById(Math.toIntExact(userId))
                    .orElseThrow(() -> new NotFoundException("Could not find user with id=" + userId));

            if (group.getMembers().contains(user)) {
                group.removeMember(user);
                groupRepository.save(group);

                return ResponseEntity.ok().body(group);
            } else {
                throw new NotFoundException("User with id=" + userId + " is not a member of the group with id=" + groupId);
            }
        } catch (DataAccessException dae) {
            throw new RuntimeException(dae.getMessage());
        }
    }



    @GetMapping("/group/{groupId:[0-9]+}/members/{userId:[0-9]+}")
    public ResponseEntity<?> getMember(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId) {
        Group group = groupRepository.findById(Math.toIntExact(groupId))
                .orElseThrow(() -> new NotFoundException("Could not find group with id=" + groupId));

        User user = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new NotFoundException("Could not find user with id=" + userId));

        if (group.getMembers().contains(user)) {
            return ResponseEntity.ok().body(user);
        } else {
            throw new NotFoundException("User with id=" + userId + " is not a member of the group with id=" + groupId);
        }
    }

    @GetMapping("/group/{groupId:[0-9]+}/nonmembers")
    public ResponseEntity<List<UserGetDTO>> getNonMembers(@PathVariable("groupId") Long id) {
        logger.info("Received request for non-members of group with id {}", id);

        Group group = groupRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new NotFoundException("Could not find group with id=" + id));

        List<User> allUsers = userRepository.findAll();
        List<User> nonMembers = allUsers.stream()
                .filter(user -> !group.getMembers().contains(user))
                .toList();


        List<UserGetDTO> nonMemberDTOs = nonMembers.stream()
                .map(UserMapper::toGetDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(nonMemberDTOs, HttpStatus.OK);
    }


    @PostMapping("/group/{groupId:[0-9]+}/members")
    public ResponseEntity<?> addMembers(@PathVariable("groupId") Long groupId, @RequestBody List<Long> userIds) {
        Group group = groupRepository.findById(groupId.intValue())
                .orElseThrow(() -> new NotFoundException("Could not find group with id=" + groupId));

        List<Integer> userIdsAsInts = userIds.stream().map(Long::intValue).collect(Collectors.toList());
        List<User> usersToAdd = userRepository.findAllById(userIdsAsInts);

        usersToAdd.forEach(user -> {
            if (!group.getMembers().contains(user)) {
                group.addMember(user);
            }
        });

        groupRepository.save(group);

        return ResponseEntity.ok().build();
    }




    @PostMapping("/group/{groupId:[0-9]+}/members/{userId:[0-9]+}")
    public ResponseEntity<?> addMember(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId) {
        try {
            Group group = groupRepository.findById(Math.toIntExact(groupId))
                    .orElseThrow(() -> new NotFoundException("Could not find group with id=" + groupId));

            User user = userRepository.findById(Math.toIntExact(userId))
                    .orElseThrow(() -> new NotFoundException("Could not find user with id=" + userId));

            if (!group.getMembers().contains(user)) {
                group.addMember(user);
                groupRepository.save(group);

                return ResponseEntity.ok().build();
            } else {
                throw new NotFoundException("User with id=" + userId + " is already a member of the group with id=" + groupId);
            }
        } catch (DataAccessException dae) {
            throw new RuntimeException(dae.getMessage());
        }
    }



    @GetMapping("/group/{groupId:[0-9]+}")
    public ResponseEntity<GroupGetFinestDTO> getGroupByID(@PathVariable("groupId") Long id) {
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

            logger.info("Responding with group {}", groupDTO);

            return ResponseEntity.ok().body(groupDTO);
        } else {
            logger.error("Could not find group with id {}", id);
            throw new NotFoundException("Could not find group with id=" + id);
        }
    }




    @GetMapping("/groupsDetail")
    public ResponseEntity<List<GroupGetFinestDTO>> getAllGroupWithMembers(){

        List<GroupGetFinestDTO> groupList = groupRepository.findAll()
                .stream()
                .map(GroupMapper::toGetFinestDto)
                .toList();

        return new ResponseEntity<>(groupList,HttpStatus.OK);
    }




    @PostMapping(value="/groups")
    public ResponseEntity<GroupGetDTO> addGroup(@Valid @RequestBody GroupCreateDTO groupCreateDTO)  {
        try
        {
        Group group = GroupMapper.toEntity(groupCreateDTO);

        Group savedGroup = groupRepository.save(group);


        return new ResponseEntity<>(GroupMapper.toGetDto(savedGroup),HttpStatus.CREATED);
        }
        catch (DataAccessException dae) {
            throw new RuntimeException(dae.getMessage());
        }
    }


    @PutMapping("/group/{groupId:[0-9]+}")
    public ResponseEntity<?> updateGroup(@PathVariable("groupId") Long id, @Valid @RequestBody GroupCreateDTO groupCreateDTO, BindingResult result) {
        logger.info("Received request to update group with id {}", id);

        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }

        try {
            Group existingGroup = groupRepository.findById(Math.toIntExact(id))
                    .orElseThrow(() -> new NotFoundException("Invalid group Id:" + id));

            Group groupUpdate = GroupMapper.toEntity(groupCreateDTO);

            groupUpdate.setId(existingGroup.getId());

            Group saveGroup = groupRepository.save(groupUpdate);

            GroupGetDTO groupGetDTO = GroupMapper.toGetDto(saveGroup);

            return new ResponseEntity<>(groupGetDTO, HttpStatus.OK);

        }         catch (DataAccessException dae) {
            throw new RuntimeException(dae.getMessage());
        }

    }


    @DeleteMapping("/groups/{id:[0-9]+}")
    @Transactional
    public ResponseEntity<Group> deleteGroup(@PathVariable("id") Long id) {
        Optional<Group> optionalGroup = groupRepository.findById(Math.toIntExact(id));

        if(optionalGroup.isPresent()){
            groupRepository.deleteGroupMembers(id);
            groupRepository.deleteGroup(id);
            return new ResponseEntity<>(optionalGroup.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    }



