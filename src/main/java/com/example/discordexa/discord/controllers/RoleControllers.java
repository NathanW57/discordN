package com.example.discordexa.discord.controllers;




import com.example.discordexa.discord.bean.Role;

import com.example.discordexa.discord.repository.RoleRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
public class RoleControllers {

    @Autowired
    private RoleRepository roleRepository;




    @GetMapping("/roles")
    @Transactional
    public ResponseEntity<List<Role>> getRoles() throws SQLException {
        List<Role> list = roleRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



    @GetMapping("/role/{id}")
    public ResponseEntity<Optional<Role>> getRole(@PathVariable("id") Integer id) throws SQLException {

        Optional<Role> optionalRole = roleRepository.findById(id);

        if(optionalRole.isPresent()) {
            return new ResponseEntity<>(optionalRole, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//
//

    @PostMapping("/role")
    public ResponseEntity<Role> addRole(@RequestBody Role role) throws SQLException {
        if (role.getId() != null){
            Optional<Role> optionalRole = roleRepository.findById(role.getId());

            if(optionalRole.isPresent()){
                roleRepository.save(role);
                return new ResponseEntity<>(role,HttpStatus.OK);
            }

            return new ResponseEntity<>(role,HttpStatus.BAD_REQUEST);
        }

        roleRepository.save(role);
        return new ResponseEntity<>(role,HttpStatus.CREATED);
    }
}
