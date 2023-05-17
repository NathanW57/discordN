package com.example.discordexa.discord.controllers;

import com.example.discordexa.discord.bean.User;
import com.example.discordexa.discord.repository.UserRepository;
import com.example.discordexa.discord.security.JwtUtils;
import com.example.discordexa.discord.security.MyUserDetails;
import com.example.discordexa.discord.security.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.regex.Pattern;


@RestController
@CrossOrigin
public class ConnexionController {


    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepository repository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody User user){

        MyUserDetails userDetails;

        try {
            userDetails = (MyUserDetails) authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword())
            ).getPrincipal();
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(jwtUtils.generateJwt(userDetails), HttpStatus.OK);
    }


    @PostMapping("/inscription")
    public ResponseEntity<User> inscription(@RequestBody User user){
        if(user.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(user.getPassword().length() <= 3){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        if(!pattern.matcher(user.getEmail()).matches()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> optionalUtilisateur = repository.findByEmail(user.getEmail());

        if(optionalUtilisateur.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);

        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

}
