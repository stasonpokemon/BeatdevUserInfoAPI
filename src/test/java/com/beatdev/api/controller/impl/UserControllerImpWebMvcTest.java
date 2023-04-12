package com.beatdev.api.controller.impl;

import com.beatdev.api.entity.User;
import com.beatdev.api.entity.dto.request.CreateUserRequestDTO;
import com.beatdev.api.entity.dto.request.UpdateStatusUserRequestDTO;
import com.beatdev.api.entity.dto.response.CreatedUserResponseDTO;
import com.beatdev.api.entity.dto.response.UpdatedStatusUserResponseDTO;
import com.beatdev.api.entity.dto.response.UserInfoResponseDTO;
import com.beatdev.api.exception.BadRequestException;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UserControllerImpWebMvcTest test class for testing UserController.
 */
@WebMvcTest(UserControllerImpl.class)
class UserControllerImpWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl userService;

    private UUID userId;


    @BeforeEach
    void initBeforeEach() {
        userId = UUID.randomUUID();
    }

    @Test
    void create_WhenCreateUserRequestDTOIsValid_Return200CodeAndCreatedUserResponseDTO() throws Exception {
        CreateUserRequestDTO createUserRequestDTO = UserTestDataFactory.buildValidCreateUserRequestDTO();
        ResponseEntity<CreatedUserResponseDTO> expectedResponse =
                new ResponseEntity<>(UserTestDataFactory.buildCreatedUserResponseDTO(), HttpStatus.OK);

        when(userService.create(createUserRequestDTO)).thenReturn(expectedResponse);

        mockMvc.perform(post("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse.getBody())));
    }

    @Test
    void create_WhenUserEmailIsExists_Return400CodeAndExceptionResponseDTO() throws Exception {
        CreateUserRequestDTO createUserRequestDTO = UserTestDataFactory.buildValidCreateUserRequestDTO();
        BadRequestException expectedBadRequestException =
                new BadRequestException(String.format("There is user with specified email: %s",
                        createUserRequestDTO.getEmail()));
        ResponseEntity<ExceptionResponseDTO> expectedResponse =
                new ResponseEntity<>(UserTestDataFactory.buildBadRequestExceptionResponseDTO(
                        expectedBadRequestException), HttpStatus.BAD_REQUEST);


        when(userService.create(createUserRequestDTO)).thenThrow(expectedBadRequestException);

        mockMvc.perform(post("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.time").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message")
                        .value(Objects.requireNonNull(expectedResponse.getBody()).getMessage()));
    }

    @Test
    void create_WhenUserUsernameIsExists_Return400CodeAndExceptionResponseDTO() throws Exception {
        CreateUserRequestDTO createUserRequestDTO = UserTestDataFactory.buildValidCreateUserRequestDTO();
        BadRequestException expectedBadRequestException =
                new BadRequestException(String.format("There is user with specified username: %s",
                        createUserRequestDTO.getUsername()));
        ResponseEntity<ExceptionResponseDTO> expectedResponse =
                new ResponseEntity<>(UserTestDataFactory.buildBadRequestExceptionResponseDTO(
                        expectedBadRequestException), HttpStatus.BAD_REQUEST);


        when(userService.create(createUserRequestDTO)).thenThrow(expectedBadRequestException);

        mockMvc.perform(post("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.time").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message")
                        .value(Objects.requireNonNull(expectedResponse.getBody()).getMessage()));
    }

    @Test
    void findUserById_WhenUserIdIsValid_Return200CodeAndUserInfoResponseDTO() throws Exception {
        ResponseEntity<UserInfoResponseDTO> expectedResponse =
                new ResponseEntity<>(UserTestDataFactory.buildUserInfoResponseDTO(), HttpStatus.OK);
        when(userService.findById(userId)).thenReturn(expectedResponse);

        mockMvc.perform(get("/users/" + userId)
                        .accept(MediaType.APPLICATION_JSON))
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
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.time").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message")
                        .value(Objects.requireNonNull(expectedResponse.getBody()).getMessage()));
    }

    /**
     * Return UpdatedStatusUserResponseDTO.
     * Test name did not fit into 120 characters.
     */
    @Test
    void
    updateUserStatus_WhenUserIdAndUserStatusIsCorrectAndUserHasNotSpecifiedStatus_Return200CodeAndUserResponseDTO()
            throws Exception {
        UpdateStatusUserRequestDTO onlineUpdateStatusUserRequestDTO =
                UserTestDataFactory.buildOnlineUpdateStatusUserRequestDTO();
        ResponseEntity<UpdatedStatusUserResponseDTO> expectedResponse =
                new ResponseEntity<>(UserTestDataFactory.buildOnlineUpdatedStatusUserResponseDTO(userId),
                        HttpStatus.OK);

        when(userService.setUserStatus(userId, onlineUpdateStatusUserRequestDTO)).thenReturn(expectedResponse);

        mockMvc.perform(patch("/users/" + userId + "/status")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(onlineUpdateStatusUserRequestDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse.getBody())));
    }

    @Test
    void
    updateUserStatus_WhenUserIdAndUserStatusIsCorrectAndUserHasSpecifiedStatus_Return400CodeAndExceptionResponseDTO()
            throws Exception {
        UpdateStatusUserRequestDTO onlineUpdateStatusUserRequestDTO =
                UserTestDataFactory.buildOnlineUpdateStatusUserRequestDTO();
        BadRequestException badRequestException =
                new BadRequestException(String.format("User with id: %s already has status: %s",
                        userId, onlineUpdateStatusUserRequestDTO.getStatus()));
        ResponseEntity<ExceptionResponseDTO> expectedResponse =
                new ResponseEntity<>(UserTestDataFactory.buildBadRequestExceptionResponseDTO(badRequestException),
                        HttpStatus.BAD_REQUEST);

        when(userService.setUserStatus(userId, onlineUpdateStatusUserRequestDTO)).thenThrow(badRequestException);

        mockMvc.perform(patch("/users/" + userId + "/status")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(onlineUpdateStatusUserRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.time").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message")
                        .value(Objects.requireNonNull(expectedResponse.getBody()).getMessage()));
    }

    @Test
    void updateUserStatus_WhenUserIdIsValidAndUserStatusIsIncorrect_Return400CodeAndExceptionResponseDTO()
            throws Exception {
        UpdateStatusUserRequestDTO incorrectUpdateStatusUserRequestDTO =
                UserTestDataFactory.buildIncorrectUpdateStatusUserRequestDTO();
        BadRequestException badRequestException =
                new BadRequestException(String.format("Wrong user status: %s",
                        incorrectUpdateStatusUserRequestDTO.getStatus()));
        ResponseEntity<ExceptionResponseDTO> expectedResponse =
                new ResponseEntity<>(UserTestDataFactory.buildBadRequestExceptionResponseDTO(badRequestException),
                        HttpStatus.BAD_REQUEST);

        when(userService.setUserStatus(userId, incorrectUpdateStatusUserRequestDTO)).thenThrow(badRequestException);

        mockMvc.perform(patch("/users/" + userId + "/status")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incorrectUpdateStatusUserRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.time").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message")
                        .value(Objects.requireNonNull(expectedResponse.getBody()).getMessage()));
    }

    @Test
    void updateUserStatus_WhenUserIdIsInvalid_Return404CodeAndExceptionResponseDTO()
            throws Exception {
        UpdateStatusUserRequestDTO onlineUpdateStatusUserRequestDTO =
                UserTestDataFactory.buildOnlineUpdateStatusUserRequestDTO();
        NotFoundException notFoundException =
                new NotFoundException(User.class, userId);
        ResponseEntity<ExceptionResponseDTO> expectedResponse =
                new ResponseEntity<>(UserTestDataFactory.buildNotFoundExceptionResponseDTO(notFoundException),
                        HttpStatus.BAD_REQUEST);

        when(userService.setUserStatus(userId, onlineUpdateStatusUserRequestDTO)).thenThrow(notFoundException);

        mockMvc.perform(patch("/users/" + userId + "/status")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(onlineUpdateStatusUserRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.time").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message")
                        .value(Objects.requireNonNull(expectedResponse.getBody()).getMessage()));
    }
}
