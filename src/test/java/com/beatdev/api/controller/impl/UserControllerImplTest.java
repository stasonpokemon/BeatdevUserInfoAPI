package com.beatdev.api.controller.impl;

import com.beatdev.api.entity.User;
import com.beatdev.api.entity.dto.response.UserInfoResponseDTO;
import com.beatdev.api.exception.NotFoundException;
import com.beatdev.api.exception.dto.ExceptionResponseDTO;
import com.beatdev.api.service.impl.UserServiceImpl;
import com.beatdev.api.util.UserTestDataFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserControllerImpl.class)
class UserControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl userService;

    private User onlineUser;

    private UUID userId;

    @BeforeEach
    void initBeforeEach() {
        onlineUser = UserTestDataFactory.buildUserOnlineUser();
        userId = UUID.randomUUID();
    }

    @Test
    void create() {
    }

    @Test
    void findUserById_WhenUserIdIsValid_Return200CodeAndUserInfoResponseDTO() throws Exception {
        ResponseEntity<UserInfoResponseDTO> expectedResponse =
                new ResponseEntity<>(UserTestDataFactory.buildUserInfoResponseDTO(), HttpStatus.OK);
        when(userService.findById(userId)).thenReturn(expectedResponse);

        mockMvc.perform(get("/users/" + userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse.getBody())));
    }

    @Test
    void findUserById_WhenUserIdIsInvalid_Return404CodeAndExceptionResponseDTO() throws Exception {
        NotFoundException expectedNotFoundException = new NotFoundException(User.class, userId);
        ResponseEntity<ExceptionResponseDTO> expectedResponse =
                new ResponseEntity<>(UserTestDataFactory.buildNotFoundExceptionResponseDTO(
                        expectedNotFoundException), HttpStatus.NOT_FOUND);
        when(userService.findById(userId)).thenThrow(expectedNotFoundException);

        mockMvc.perform(get("/users/" + userId))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"));
//                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse.getBody())));
    }

    @Test
    void updateUserStatus() {
    }
}