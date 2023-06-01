package com.example.discordexa.discord.bean;//package bean;


import com.example.discordexa.discord.Enum.EVisibility;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "channel")
public class Channel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cha_id")
    private Long id;

    @Column(name = "cha_name")
    private String name;

    @Column(name = "cha_visibility")
    private EVisibility visibility;

    @OneToMany
    private List<Message> messages = new ArrayList<>();

    @OneToMany
    private List<Meeting> meetings = new ArrayList<>();



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "is_allowed_in", joinColumns = @JoinColumn(name = "cha_id", referencedColumnName = "cha_id"), inverseJoinColumns = @JoinColumn(name = "usr_id", referencedColumnName = "usr_id"))
    @ToString.Exclude
    private Set<User> members = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "has_subscribed_to",
            joinColumns = @JoinColumn(name = "cha_id",referencedColumnName = "cha_id"),
            inverseJoinColumns = @JoinColumn (name = "usr_id", referencedColumnName = "usr_id")
    )
    private List<User> subscribers;

    public Channel() {

    }





//    public void addMessage(ChannelMessage message) {
//        messages.add(new Message(message.getContent(), message.getSender(), this));
//    }


//    public void addMeeting(Meeting meeting) {
//        meetings.add(meeting);
//    }

//    public void removeMeeting(Meeting meeting) {
//        meetings.remove(meeting);
//    }


//    public void addMember(User member) {
//        members.add(member);
//    }
//
//    public void removeMember(User member) {
//        members.remove(member);
//    }
//
//
//
//    public void addSubscriber(User subscriber) {
//        subscribers.add(subscriber);
//    }

//    public void removeSubscriber(User subscriber) {
//        subscribers.remove(subscriber);
//    }

    public void notifySubscribers() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = StringEscapeUtils.escapeHtml4(name.trim());
    }

    public void setVisibility(EVisibility visibility) {
        this.visibility = visibility;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }
}
