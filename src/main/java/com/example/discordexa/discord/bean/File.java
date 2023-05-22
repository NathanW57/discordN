package com.example.discordexa.discord.bean;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "file")
public class File {

    @Id
    @Column(name = "fil_id")
    private Long id;

    @Column(name = "fil_name")
    private String name;

    @Column(name = "fil_path")
    private String path;

}
