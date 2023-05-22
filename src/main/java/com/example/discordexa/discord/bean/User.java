package com.example.discordexa.discord.bean;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = StringEscapeUtils.escapeHtml4(email.trim().toLowerCase());
    }

    public void setFirstname(String firstname) {
        this.firstname = StringEscapeUtils.escapeHtml4(firstname.toLowerCase().trim());
    }

    public void setLastname(String lastname) {
        this.lastname = StringEscapeUtils.escapeHtml4(lastname.toUpperCase().trim());
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
