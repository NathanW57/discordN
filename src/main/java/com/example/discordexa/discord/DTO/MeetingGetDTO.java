package com.example.discordexa.discord.DTO;

public class MeetingGetDTO {
    private long id;
    private String name;
    private UserGetDTO organizer;
    private int datetime;
    private int duration;


    public MeetingGetDTO(long id, String name, UserGetDTO organizer, int datetime, int duration) {
        this.id = id;
        this.name = name;
        this.organizer = organizer;
        this.datetime = datetime;
        this.duration = duration;
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

    public UserGetDTO getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UserGetDTO organizer) {
        this.organizer = organizer;
    }

    public int getDatetime() {
        return datetime;
    }

    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
