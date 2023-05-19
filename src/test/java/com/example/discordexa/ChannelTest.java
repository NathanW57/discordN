package com.example.discordexa;

import com.example.discordexa.discord.bean.Channel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChannelTest {

    Channel channel;

    @BeforeEach
    public void setup(){
        channel = new Channel();
    }

    @Test
    public void nameShouldBeTrim(){
        channel.setName("         test        ");
        assertEquals("test",channel.getName());
    }

    @Test
    public void nameShouldBeEscapeHtml(){
        channel.setName("<>&\"");
        assertEquals("&lt;&gt;&amp;&quot;",channel.getName());
    }
}
