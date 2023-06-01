package com.example.discordexa;


import com.example.discordexa.discord.bean.User;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    private User user;
    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()).build();
        user = new User();
    }

    @Test
    void createUser_idUserNull() {

        assertNull(user.getId());
    }

    @Test
    void testSetId() {
        Long id = 1L;
        user.setId(id);
        assertEquals(id, user.getId());
    }
    @Test
    public void emailShouldBeTrim(){
        String email = "    Test@Email.com    ";
        String expectedEmail = StringEscapeUtils.escapeHtml4(email.trim().toLowerCase());
        user.setEmail(email);
        assertEquals(expectedEmail, user.getEmail());
    }

    @Test
    public void emailShoudBeLowerCase(){
        user.setEmail("TEST@TEST.com");
        assertEquals("test@test.com",user.getEmail());
    }

    @Test
    public void lastNameShoudBeUpperCase(){
        user.setLastname("test");
        assertEquals("TEST",user.getLastname());
    }

    @Test
    public void lastNameShouldBeTrim(){
        user.setLastname("         TEST         ");
        assertEquals("TEST",user.getLastname());
    }

    @Test
    public void lastNameShouldBeEscapeHtml(){
        user.setLastname("<>&\"");
        assertEquals("&lt;&gt;&amp;&quot;",user.getLastname());
    }

    @Test
    public void firstNameShouldBeTrim(){
        user.setFirstname("         test       ");
        assertEquals("test",user.getFirstname());
    }

    @Test
    public void firstNameShouldBeEscapeHtml(){
        user.setFirstname("<>&\"");
        assertEquals("&lt;&gt;&amp;&quot;",user.getFirstname());
    }

    @Test
    void testToString() {
        user.setId(1L);
        user.setEmail("john@example.com");
        user.setLastname("Doe");
        user.setFirstname("John");
        user.setPassword("password");

        String expected = "User{" +
                "id=" + user.getId() +
                ", email='" + user.getEmail() + '\'' +
                ", firstname='" + user.getFirstname() + '\'' +
                ", lastname='" + user.getLastname() + '\'' +
                ", password='" + user.getPassword() + '\'' +
                ", role=" + user.getRole() +
                '}';;
        assertEquals(expected, user.toString());
    }
}
