package com.example.discordexa.discord.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ConfigurationProperties
public class EmailConfig {

    @Value("${udeesa.email.sender.host}")
    private String host;

    @Value("${udeesa.email.sender.user}")
    private String user;

    @Value("${udeesa.email.sender.password}")
    private String password;

    @Value("${udeesa.email.sender.debug}")
    private Boolean debug;

    private static final int _GMAIL_PORT = 587;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(_GMAIL_PORT);

        mailSender.setUsername(user);
        mailSender.setPassword(password);

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.debug", debug);

        return mailSender;
    }

}
