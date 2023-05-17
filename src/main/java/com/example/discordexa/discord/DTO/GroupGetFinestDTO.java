package com.example.discordexa.discord.DTO;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupGetFinestDTO {

    private long id;
    private String name;

    private List<UserGetDTO> members;


    public GroupGetFinestDTO(){}

}
