package com.example.discordexa.discord.bean;//package bean;


import com.example.discordexa.discord.Enum.EVisibility;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringEscapeUtils;


import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Channel {
    @Id
    @Column(name = "cha_id")
    private Long id;

    @Column(name = "cha_name")
    private String name;

    @Column(name = "cha_visibility")
    private EVisibility visibility;

//    @OneToMany
//    private List<Message> messages;

//    @OneToMany
//    private List<Meeting> meetings;

    @ManyToMany
    @JoinTable(name = "is_member_of",
            joinColumns = @JoinColumn(name = "cha_id",referencedColumnName = "cha_id"),
            inverseJoinColumns = @JoinColumn (name = "usr_id", referencedColumnName = "usr_id")
    )
    private List<User> members;

//    @ManyToMany
//    @JoinTable(name = "is_notified_of",
//            joinColumns = @JoinColumn(name = "cha_id",referencedColumnName = "cha_id"),
//            inverseJoinColumns = @JoinColumn (name = "not_id", referencedColumnName = "not_id")
//    )
//    private List<User> subscribers;

//    @ManyToOne
//    @JoinColumn(name = "cha_visibility")
//    private List<EVisibility> visibility;


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

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
