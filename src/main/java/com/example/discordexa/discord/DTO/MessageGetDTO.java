package com.example.discordexa.discord.DTO;

public class MessageGetDTO {
    private long id;
    private UserGetDTO user;
    private String content;
    private String fileName;
    private String filePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserGetDTO getUser() {
        return user;
    }

    public void setUser(UserGetDTO user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
