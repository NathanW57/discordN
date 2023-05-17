package com.example.discordexa.discord.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChannelCreateDTO {

    @NotNull(message = "Name required")
    @NotBlank(message = "Name required")
    private String name;
    @NotNull(message = "Visibility required")
    private String visibility;

    public ChannelCreateDTO() {

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

    public boolean isValidName() {
        return name != null && !name.trim().isEmpty();
    }


}
