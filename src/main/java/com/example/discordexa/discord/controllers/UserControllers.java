package com.example.discordexa.discord.controllers;





import com.example.discordexa.discord.DTO.UserCreateDTO;
import com.example.discordexa.discord.DTO.UserGetDTO;

import com.example.discordexa.discord.DTO.UserGetFinestDTO;
import com.example.discordexa.discord.DTO.UserUpdateDTO;
import com.example.discordexa.discord.Enum.Erole;
import com.example.discordexa.discord.bean.Role;
import com.example.discordexa.discord.bean.User;
import com.example.discordexa.discord.exception.UserException;
import com.example.discordexa.discord.mapper.UserMapper;
import com.example.discordexa.discord.repository.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.validation.BindingResult;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
public class UserControllers {



    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private GroupRepository groupRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    public UserControllers(UserRepository userRepository) {

    }




    @GetMapping("/users")
    public ResponseEntity<List<UserGetDTO>> getAllUsers() throws SQLException {
        List<UserGetDTO> userList = userRepository.findAll()
                .stream()
                .map(UserMapper::toGetDto)
                .toList();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }




    @GetMapping("/user/{id:[0-9]+}")
    public ResponseEntity<UserGetDTO> getUser(@PathVariable("id") int id) throws SQLException {

        return userRepository.findById(id).map(UserMapper::toGetDto)
            .map(userGetDTO -> new ResponseEntity<>(userGetDTO, HttpStatus.OK)).
                orElseThrow(() -> new UserException("Invalid user Id:" + id));
    }




    @GetMapping("/userFinest/{id:[0-9]+}")
    public ResponseEntity<UserGetFinestDTO> getUserFinest(@PathVariable("id") long id) throws SQLException {

       return userRepository.findByIdRole(id).map(UserMapper::toGetFinestDto)
                .map(userGetFinestDTO -> new ResponseEntity<>(userGetFinestDTO, HttpStatus.OK)).
                orElseThrow(() -> new UserException("Invalid user Id:" + id));
    }


    @PostMapping(value = "/users")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserCreateDTO userDTO, BindingResult result) {

        try {

            User newUser = UserMapper.toEntity(userDTO);
            newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            Role role = roleRepository.findByName(Erole.ROLE_USER);
            newUser.addRole(role);


            User savedUser = userRepository.save(newUser);
            return new ResponseEntity<>(UserMapper.toGetDto(savedUser), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
    }




    @DeleteMapping("/user/{id:[0-9]+}")
    @Transactional
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) throws SQLException {
        Optional<User> optionalUtilisateurs = userRepository.findById(Math.toIntExact(id));

        if(optionalUtilisateurs.isPresent()){
            channelRepository.deleteUserFromChannels(id);
            groupRepository.deleteUserFromGroups(id);
            messageRepository.deleteAllBySenderId(id);
            notificationRepository.deleteAllByReceiverId(id);
            userRepository.deleteById(Math.toIntExact(id));
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }




    @PutMapping(value = "/users/{id:[0-9]+}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }

        try {
            User existingUser = userRepository.findById(Math.toIntExact(id))
                    .orElseThrow(() -> new UserException("Invalid user Id:" + id));


            User updatedUser = UserMapper.UpdatetoEntity(userUpdateDTO);

            updatedUser.setId(existingUser.getId());
            updatedUser.setRole(existingUser.getRole());

            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // Ensure the password is encoded

            User savedUser = userRepository.save(updatedUser);

            UserGetDTO userGetDTO = UserMapper.toGetDto(savedUser);

            return new ResponseEntity<>(userGetDTO, HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
    }

}




