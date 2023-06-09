package com.example.discordexa.discord.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChannelGetFinestDTO {
    private long id;
    private String name;
    private String visibility;

    private List<MessageGetDTO> messages;

    private List<UserGetDTO> members;

    private List<UserGetDTO> subscribers;


    public ChannelGetFinestDTO(){}

    public ChannelGetFinestDTO(long id, String name, String visibility) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
