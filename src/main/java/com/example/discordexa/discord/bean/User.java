package com.example.discordexa.discord.bean;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id
    @Column(name = "usr_id")
    private Integer id;

    @Column(name = "usr_email")
    private String email;

    @Column(name = "usr_firstname")
    private String firstname;

    @Column(name = "usr_lastname")
    private String lastname;


    @Column(name = "usr_password")
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> role;


}
