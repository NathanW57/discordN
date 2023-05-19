package com.example.discordexa;

import com.example.discordexa.discord.bean.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GroupTest {

    Group group;

    @BeforeEach
    public void setup(){
        group = new Group();
    }

    @Test
    public void nameShouldBeTrim(){
        group.setName("    test    ");
        assertEquals("test",group.getName());
    }

    @Test
    public void nameShouldBeEscapeHtml(){
        group.setName("<>&\"");
        assertEquals("&lt;&gt;&amp;&quot;",group.getName());
    }
}
