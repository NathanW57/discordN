package com.example.discordexa.discord.DTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeetingCreateDTO {
    private String name;
    private int datetime;
    private int duration;
    private int organizerId;

    public MeetingCreateDTO(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public boolean isValidName() {
        if (name == null || name.isEmpty()) {
            return false;
        }
        String regex = "^.{2,50}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name.trim());
        return matcher.matches();
    }

    public boolean isValidDuration(){
        return duration > 1 && duration < 1440;
    }

    public boolean isValidOrganize(){
        return organizerId > 0;
    }



}
