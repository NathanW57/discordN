package com.example.discordexa.discord.DTO;

public class ChannelGetDTO {
    private long id;
    private String name;
    private String visibility;

    public ChannelGetDTO(){}

    public ChannelGetDTO(long id, String name, String visibility) {
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
