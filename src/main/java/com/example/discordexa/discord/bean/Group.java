package com.example.discordexa.discord.bean;




import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_group")
public class Group {
    @Id
    @Column(name = "gro_id")
    private long id;

    @Column(name = "gro_name")
    private String name;

    @ManyToMany
    @JoinTable(name = "is_member_of",
            joinColumns = @JoinColumn(name = "gro_id", referencedColumnName = "gro_id"),
            inverseJoinColumns = @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    )
    private List<User> users ;

    public Group(String name) {
        this.name = name.trim();
    }
    public Group(){}

    public Group(long id, String lastname) {
        this.name = lastname;
        this.id = id;
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

    public void removeMember(User user) {
        this.users.remove(user);
    }

    public void addMember(User user){
        this.users.add(user);
    }

}
