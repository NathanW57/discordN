package com.example.discordexa.discord.DTO;

import com.example.discordexa.discord.bean.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserGetFinestDTO {
    private long id;
    private String email;
    private String lastname;
    private String firstname;
    private Role[] role;
}
