package com.example.discordexa.discord.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GroupGetDTO {

    private long id;
    private String name;


    public GroupGetDTO(){}


    @Override
    public String toString() {
        return "GroupGetDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
