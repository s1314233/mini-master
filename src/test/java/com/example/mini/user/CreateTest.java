package com.example.mini.user;

import com.example.mini.MiniApplication;
import com.example.mini.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@SpringBootTest(classes = MiniApplication.class)
@Service
public class CreateTest {
    @Resource
    private UserServiceImpl userServiceImpl;

    @Test
    void create() {
        String username = "dongfanger";
        String usernameCreated = userServiceImpl.create(username);
        Assertions.assertEquals(username, usernameCreated);
    }
}
