package com.example.discordexa.discord.controllers;





import com.example.discordexa.discord.DTO.UserCreateDTO;
import com.example.discordexa.discord.DTO.UserGetDTO;

import com.example.discordexa.discord.DTO.UserGetFinestDTO;
import com.example.discordexa.discord.Enum.Erole;
import com.example.discordexa.discord.bean.ApiResponse;
import com.example.discordexa.discord.bean.Role;
import com.example.discordexa.discord.bean.User;
import com.example.discordexa.discord.repository.RoleRepository;
import com.example.discordexa.discord.repository.UserRepository;
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
    private RoleRepository roleRepository;

    public UserControllers(UserRepository userRepository) {

    }


    /**
     * Retrieves all users.
     *
     * @return a list of users
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserGetDTO>> getUsers() throws SQLException {
        List<User> userList = userRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return new ResponseEntity<>(userList
                .stream()
                .map((user) -> mapper.map(user, UserGetDTO.class))
                .toList(), HttpStatus.OK);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the user ID
     * @return a User object and OK status or a NOT_FOUND status if not found
     */

    @GetMapping("/user/{id}")
    public ResponseEntity<UserGetDTO> getUser(@PathVariable("id") int id) throws SQLException {

        Optional<User> optional = userRepository.findById(id);

        if(optional.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            UserGetDTO userGetDTO = mapper.map(optional.get(), UserGetDTO.class);
            return new ResponseEntity<>(userGetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    /**
     * Retrieves a user by ID.
     *
     * @param id the user ID
     * @return a User object and OK status or a NOT_FOUND status if not found
     */
    @GetMapping("/userFinest/{id}")
    public ResponseEntity<UserGetFinestDTO> getUserFinest(@PathVariable("id") long id) throws SQLException {

        Optional<User> optional = userRepository.findByIdRole(id);

        if(optional.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            UserGetFinestDTO userGetDTO = mapper.map(optional.get(), UserGetFinestDTO.class);
            return new ResponseEntity<>(userGetDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
            ModelMapper mapper = new ModelMapper();

            // Créer un nouvel utilisateur avec le rôle ROLE_USER
            User newUser = mapper.map(userDTO, User.class);
            newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            Role role = roleRepository.findByName(Erole.ROLE_USER);
            newUser.addRole(role);

            // Sauvegarder l'utilisateur
            User savedUser = userRepository.save(newUser);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);


        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
    }







    //
//    /**
//     * Updates an existing user.
//     *
//     * @param user the User object with updated information
//     * @return the updated User object or an error status
//     */
//    @PUT
//    @Path("/users")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateUser(User user) throws SQLException {
//        User updatedUser = userRepository.updateUser(user);
//        return Response.ok(updatedUser).build();
//    }
//
//
//    /**
//     * Deletes a user by ID.
//     *
//     * @param id the user ID
//     * @return an ok status or an error status
//     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) throws SQLException {
        Optional<User> optionalUtilisateurs = userRepository.findById(id);

        if(optionalUtilisateurs.isPresent()){
            userRepository.deleteById(id);
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
//
//    @GET
//    @Path("/usersNotified")
//    public Response getAllUserNotified() throws SQLException {
//        List<UserNotified>  userNotifiedList = userRepository.selectAllUserNotified();
//        return Response.ok(userNotifiedList).build();
//    }
}




