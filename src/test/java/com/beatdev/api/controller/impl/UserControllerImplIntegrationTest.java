package com.beatdev.api.controller.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
class UserControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() {
    }

    @Test
    void findUserById() {
    }

    @Test
    void updateUserStatus() {
    }
}
