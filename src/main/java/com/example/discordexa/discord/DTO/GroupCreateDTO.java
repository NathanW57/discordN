package com.example.discordexa.discord.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class GroupCreateDTO {
    @NotNull(message = "Group is required")
    @NotBlank(message = "Group is required")
    @Size(min = 2,max = 50,message = "Group Name must be 2-50 characters")
    private String name;

    public GroupCreateDTO(){
    }



}
