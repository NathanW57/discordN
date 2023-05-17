package com.example.discordexa.discord.bean;

public class ErrorDetail {
    private String code;
    private String target;
    private String message;

    public ErrorDetail(String code, String target, String message) {
        this.code = code;
        this.target = target;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
