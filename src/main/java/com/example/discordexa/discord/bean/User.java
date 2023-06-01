package com.example.discordexa.discord.bean;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "user")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_email", unique = true)
    @NotNull(message = "ne peut être vide")
    @Pattern(regexp = "^[a-zA-Z0-9-_]+\\.*[a-zA-Z0-9-_]*@([a-zA-Z0-9]+\\.{1})+([a-zA-Z]){2,3}$", message = "doit " +
            "être" + " un email valide")
    private String email;

    @Column(name = "usr_firstname")
    @NotNull(message = "ne peut être vide")
    @NotBlank
    @Size(min = 2, max = 50)
    private String firstname;

    @Column(name = "usr_lastname")
    @NotNull(message = "ne peut être vide")
    @NotBlank
    @Size(min = 2, max = 50)
    private String lastname;


    @Column(name = "usr_password")
    @NotNull(message = "ne peut être vide")
    @Size(min = 8, max = 200)
    @NotBlank
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "has_role", joinColumns = @JoinColumn(name = "usr_id", referencedColumnName = "usr_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "rol_id"))
    private Set<Role> role;

    public void setId(Long id) {
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

    public void setRole(Set<Role> role) {
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

    public void addRole(Role role) {
        if (this.role == null) {
            this.role = new HashSet<>();
        }
        this.role.add(role);
    }


}
