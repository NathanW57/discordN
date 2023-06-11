package com.example.discordexa.discord.mapper;

import com.example.discordexa.discord.DTO.UserUpdateDTO;
import com.example.discordexa.discord.bean.User;
import com.example.discordexa.discord.DTO.UserCreateDTO;
import com.example.discordexa.discord.DTO.UserGetDTO;
import com.example.discordexa.discord.DTO.UserGetFinestDTO;
import com.example.discordexa.discord.bean.Role;
import java.util.HashSet;
import java.util.Set;

public class UserMapper {

    public static User toEntity(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setEmail(userCreateDTO.getEmail());
        user.setFirstname(userCreateDTO.getFirstname());
        user.setLastname(userCreateDTO.getLastname());
        user.setPassword(userCreateDTO.getPassword());
        return user;
    }

    public static UserGetDTO toGetDto(User user) {
        UserGetDTO userGetDTO = new UserGetDTO();
        userGetDTO.setId(user.getId());
        userGetDTO.setEmail(user.getEmail());
        userGetDTO.setFirstname(user.getFirstname());
        userGetDTO.setLastname(user.getLastname());
        return userGetDTO;
    }

    public static UserGetFinestDTO toGetFinestDto(User user) {
        UserGetFinestDTO userGetFinestDTO = new UserGetFinestDTO();
        userGetFinestDTO.setId(user.getId());
        userGetFinestDTO.setEmail(user.getEmail());
        userGetFinestDTO.setFirstname(user.getFirstname());
        userGetFinestDTO.setLastname(user.getLastname());
        Set<Role> roles = user.getRole();
        if (roles != null) {
            userGetFinestDTO.setRole(roles.toArray(new Role[0]));
        } else {
            userGetFinestDTO.setRole(new Role[0]);
        }
        return userGetFinestDTO;
    }


    public static User UpdatetoEntity(UserUpdateDTO userUpdateDTO) {
        User user = new User();
        user.setEmail(userUpdateDTO.getEmail());
        user.setFirstname(userUpdateDTO.getFirstname());
        user.setLastname(userUpdateDTO.getLastname());
        user.setPassword(userUpdateDTO.getPassword());
        user.setRole(userUpdateDTO.getRole());
        return user;
    }

}
