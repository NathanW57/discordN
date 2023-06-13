package com.example.discordexa.discord.bean;




import com.example.discordexa.discord.Enum.Erole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "rol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rol_name")
    @ToString.Exclude
    private Erole name;

}
