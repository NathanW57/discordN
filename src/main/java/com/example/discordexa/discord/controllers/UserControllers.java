package com.example.discordexa.discord.controllers;





import com.example.discordexa.discord.DTO.UserCreateDTO;
import com.example.discordexa.discord.DTO.UserGetDTO;

import com.example.discordexa.discord.DTO.UserGetFinestDTO;
import com.example.discordexa.discord.DTO.UserUpdateDTO;
import com.example.discordexa.discord.Enum.Erole;
import com.example.discordexa.discord.bean.ApiResponse;
import com.example.discordexa.discord.bean.Role;
import com.example.discordexa.discord.bean.User;
import com.example.discordexa.discord.mapper.UserMapper;
import com.example.discordexa.discord.repository.*;
import jakarta.inject.Qualifier;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
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



    /**
     * Retrieves all users.
     *
     * @return a list of users
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserGetDTO>> getAllUsers() throws SQLException {
        List<UserGetDTO> userList = userRepository.findAll()
                .stream()
                .map(UserMapper::toGetDto)
                .toList();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    /**
     * Retrieves a user by ID.
     *
     * @param id the user ID
     * @return a User object and OK status or a NOT_FOUND status if not found
     */

    @GetMapping("/user/{id}")
    public ResponseEntity<UserGetDTO> getUser(@PathVariable("id") int id) throws SQLException {

        return userRepository.findById(id).map(UserMapper::toGetDto)
            .map(userGetDTO -> new ResponseEntity<>(userGetDTO, HttpStatus.OK)).
                orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }



    /**
     * Retrieves a user by ID.
     *
     * @param id the user ID
     * @return a User object and OK status or a NOT_FOUND status if not found
     */
    @GetMapping("/userFinest/{id}")
    public ResponseEntity<UserGetFinestDTO> getUserFinest(@PathVariable("id") long id) throws SQLException {

       return userRepository.findByIdRole(id).map(UserMapper::toGetFinestDto)
                .map(userGetFinestDTO -> new ResponseEntity<>(userGetFinestDTO, HttpStatus.OK)).
                orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }
//
//    /**
//     * Adds a new user.
//     *
//     * @param userDTO the UserCreateDTO object
//     * @return a UserGetDTO object with the generated ID or an error status
//     */

    @PostMapping(value = "/users")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserCreateDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }

        try {
            // Créer un nouvel utilisateur avec le rôle ROLE_USER
            User newUser = UserMapper.toEntity(userDTO);
            newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            Role role = roleRepository.findByName(Erole.ROLE_USER);
            newUser.addRole(role);

            // Sauvegarder l'utilisateur
            User savedUser = userRepository.save(newUser);
            return new ResponseEntity<>(UserMapper.toGetDto(savedUser), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
    }







//    /**
//     * Deletes a user by ID.
//     *
//     * @param id the user ID
//     * @return an ok status or an error status
//     */
    @DeleteMapping("/user/{id}")
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




    //    /**
//     * Updates an existing user.
//     *
//     * @param user the User object with updated information
//     * @return the updated User object or an error status
//     */
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }

        try {
            User existingUser = userRepository.findById(Math.toIntExact(id))
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

            // Map UserUpdateDTO to User entity
            User updatedUser = UserMapper.UpdatetoEntity(userUpdateDTO);

            // We need to make sure we do not change the ID and roles during the update
            updatedUser.setId(existingUser.getId());
            updatedUser.setRole(existingUser.getRole());

            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // Ensure the password is encoded

            // Save the updated user
            User savedUser = userRepository.save(updatedUser);

            // Map saved User entity to UserGetDTO
            UserGetDTO userGetDTO = UserMapper.toGetDto(savedUser);

            return new ResponseEntity<>(userGetDTO, HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
    }

}




