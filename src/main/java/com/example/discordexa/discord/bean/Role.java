package com.example.discordexa.discord.bean;




import com.example.discordexa.discord.Enum.Erole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @Column(name = "rol_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "rol_name")
    @ToString.Exclude
    private Erole name;

}
