package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class UserServiceTests {

    @Value("${testaccount1.firstname}")
    private String firstname;

    @Value("${testaccount1.lastname}")
    private String lastname;

    @Value("${testaccount1.username}")
    private String username;

    @Value("${testaccount1.password}")
    private String password;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isUsernameAvailable() {
    }

    @Test
    void createUser() {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setPassword(password);
        userService.createUser(user);
        User user1 = userService.getUserByUsername(username);
        Assertions.assertEquals(user.getFirstname(),user1.getFirstname());
        Assertions.assertEquals(user.getLastname(),user1.getLastname());
        Assertions.assertEquals(user.getPassword(),user1.getPassword());
    }

    @Test
    void getUser() {
    }
}