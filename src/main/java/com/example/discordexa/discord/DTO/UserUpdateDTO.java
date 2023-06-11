package com.example.discordexa.discord.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import com.example.discordexa.discord.bean.Role;

import java.util.Set;

@Getter
@Setter
public class UserUpdateDTO {

    @NotNull(message = "ne peut être vide")
    @Pattern(regexp = "^[a-zA-Z0-9-_]+\\.*[a-zA-Z0-9-_]*@([a-zA-Z0-9]+\\.{1})+([a-zA-Z]){2,3}$", message = "doit " +
            "être" + " un email valide")
    private String email;

    @NotNull(message = "ne peut être vide")
    @NotBlank
    @Size(min = 2, max = 50)
    private String firstname;

    @NotNull(message = "ne peut être vide")
    @NotBlank
    @Size(min = 2, max = 50)
    private String lastname;

    @NotNull(message = "ne peut être vide")
    @Size(min = 8, max = 200)
    @NotBlank
    private String password;

    private Set<Role> role;
}