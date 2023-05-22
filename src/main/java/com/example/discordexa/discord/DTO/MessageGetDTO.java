package com.example.discordexa.discord.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;

@Getter
@Setter
@ToString
@Data
public class MessageGetDTO {

    public MessageGetDTO() {
    }

    private long id;
    private UserGetDTO sender;
    private String content;
    private String fileName;
    private String filePath;

}
