package com.example.discordexa.discord.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserGetDTO {

    private long id;
    private String email;
    private String lastname;
    private String firstname;


    public UserGetDTO(){}
    public UserGetDTO( String email, String lastname, String firstname) {
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    @Override
    public String toString() {
        return "UserGetDTO{" +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }
}


