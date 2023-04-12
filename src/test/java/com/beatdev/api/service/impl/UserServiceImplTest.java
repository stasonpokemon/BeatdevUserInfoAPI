package com.beatdev.api.service.impl;

import com.beatdev.api.entity.User;
import com.beatdev.api.entity.dto.request.CreateUserRequestDTO;
import com.beatdev.api.entity.dto.request.UpdateStatusUserRequestDTO;
import com.beatdev.api.entity.dto.response.CreatedUserResponseDTO;
import com.beatdev.api.entity.dto.response.UpdatedStatusUserResponseDTO;
import com.beatdev.api.entity.dto.response.UserInfoResponseDTO;
import com.beatdev.api.entity.mapper.UserMapper;
import com.beatdev.api.exception.BadRequestException;
import com.beatdev.api.exception.NotFoundException;
import com.beatdev.api.repository.UserRepository;
import com.beatdev.api.util.UserTestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * UserServiceImplTest test class for testing UserService.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private UUID userId;

    private User onlineUser;

    private User offlineUser;

    private CreateUserRequestDTO createUserRequestDTO;

    private UpdateStatusUserRequestDTO onlineUpdateStatusUserRequestDTO;

    private UpdateStatusUserRequestDTO offlineUpdateStatusUserRequestDTO;

    private UpdateStatusUserRequestDTO incorrectUpdateStatusUserRequestDTO;

    @BeforeEach
    void initBeforeEach() {
        userId = UUID.randomUUID();
        onlineUser = UserTestDataFactory.buildUserOnlineUser();
        offlineUser = UserTestDataFactory.buildUserOfflineUser();
        createUserRequestDTO = UserTestDataFactory.buildValidCreateUserRequestDTO();
        onlineUpdateStatusUserRequestDTO = UserTestDataFactory.buildOnlineUpdateStatusUserRequestDTO();
        offlineUpdateStatusUserRequestDTO = UserTestDataFactory.buildOfflineUpdateStatusUserRequestDTO();
        incorrectUpdateStatusUserRequestDTO = UserTestDataFactory.buildIncorrectUpdateStatusUserRequestDTO();
    }

    @Test
    void findById_WhenUserIdIsValid_ReturnUserInfoResponseDTO() {
        onlineUser.setId(userId);
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        UserInfoResponseDTO expectedUserInfoResponseDTO = userMapper.userToUserInfoResponseDTO(onlineUser);
        when(userRepository.findById(userId)).thenReturn(Optional.of(onlineUser));

        ResponseEntity<UserInfoResponseDTO> response = userService.findById(userId);
        UserInfoResponseDTO responseBody = response.getBody();

        assertNotNull(response);
        assertNotNull(responseBody);
        assertEquals(expectedHttpStatus, response.getStatusCode());
        assertEquals(expectedUserInfoResponseDTO, responseBody);
        verify(userRepository).findById(userId);
    }

    @Test
    void findById_WhenUserIdIsInValid_ThrowsNotFoundException() {
        String expectedExceptionMessage = new NotFoundException(User.class, userId).getMessage();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> userService.findById(userId));

        assertNotNull(notFoundException);
        assertEquals(expectedExceptionMessage, notFoundException.getMessage());
        verify(userRepository).findById(userId);
    }

    @Test
    void create_WhenDTODataIsValid_ReturnCreatedUserResponseDTO() {
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        User userForSave = UserTestDataFactory.buildUserForCreateFromCreateUserRequestDTO(createUserRequestDTO);
        User savedUser = UserTestDataFactory.buildUserFromUserAndId(userForSave, userId);
        CreatedUserResponseDTO expectedCreatedUserResponseDTO =
                userMapper.userToCreatedUserResponseDTO(savedUser);
        when(userRepository.findByUsername(userForSave.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(userForSave.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(userForSave)).thenReturn(savedUser);

        ResponseEntity<CreatedUserResponseDTO> response = userService.create(createUserRequestDTO);
        CreatedUserResponseDTO responseBody = response.getBody();

        assertNotNull(response);
        assertNotNull(responseBody);
        assertEquals(expectedHttpStatus, response.getStatusCode());
        assertEquals(expectedCreatedUserResponseDTO, responseBody);
        verify(userRepository).findByUsername(userForSave.getUsername());
        verify(userRepository).findByEmail(userForSave.getEmail());
        verify(userRepository).save(userForSave);
    }

    @Test
    void create_WhenUserWithSpecifiedUsernameIsExists_ThrowsBadRequestException() {
        User userForSave = UserTestDataFactory.buildUserForCreateFromCreateUserRequestDTO(createUserRequestDTO);
        String expectedExceptionMessage = String.format("There is user with specified username: %s",
                userForSave.getUsername());

        when(userRepository.findByUsername(userForSave.getUsername())).thenReturn(Optional.of(onlineUser));

        BadRequestException badRequestException =
                assertThrows(BadRequestException.class, () -> userService.create(createUserRequestDTO));

        assertNotNull(badRequestException);
        assertEquals(expectedExceptionMessage, badRequestException.getMessage());
        verify(userRepository).findByUsername(userForSave.getUsername());
        verify(userRepository, never()).findByEmail(userForSave.getEmail());
        verify(userRepository, never()).save(userForSave);
    }

    @Test
    void create_WhenUserWithSpecifiedEmailIsExists_ThrowsBadRequestException() {
        User userForSave = UserTestDataFactory.buildUserForCreateFromCreateUserRequestDTO(createUserRequestDTO);
        String expectedExceptionMessage = String.format("There is user with specified email: %s",
                userForSave.getEmail());

        when(userRepository.findByUsername(userForSave.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(userForSave.getEmail())).thenReturn(Optional.of(onlineUser));

        BadRequestException badRequestException =
                assertThrows(BadRequestException.class, () -> userService.create(createUserRequestDTO));

        assertNotNull(badRequestException);
        assertEquals(expectedExceptionMessage, badRequestException.getMessage());
        verify(userRepository).findByUsername(userForSave.getUsername());
        verify(userRepository).findByEmail(userForSave.getEmail());
        verify(userRepository, never()).save(userForSave);
    }

    /**
     * Return UpdatedStatusUserResponseDTO.
     * Test name did not fit into 120 characters.
     */
    @Test
    void setUserStatus_WhenUserIdIsValidAndUserStatusIsCorrectAndUserHasNotOfflineStatus_ReturnUserResponseDTO() {
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        onlineUser.setId(userId);
        UpdatedStatusUserResponseDTO expectedUpdatedStatusUserResponseDTO =
                UserTestDataFactory.buildOfflineUpdatedStatusUserResponseDTO(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(onlineUser));

        ResponseEntity<UpdatedStatusUserResponseDTO> response =
                userService.setUserStatus(userId, offlineUpdateStatusUserRequestDTO);
        UpdatedStatusUserResponseDTO responseBody = response.getBody();

        assertNotNull(response);
        assertNotNull(responseBody);
        assertEquals(expectedHttpStatus, response.getStatusCode());
        assertEquals(expectedUpdatedStatusUserResponseDTO, responseBody);
        verify(userRepository).findById(userId);
    }

    /**
     * Return UpdatedStatusUserResponseDTO.
     * Test name did not fit into 120 characters.
     */
    @Test
    void setUserStatus_WhenUserIdIsValidAndUserStatusIsCorrectAndUserHasNotOnlineStatus_ReturnUserResponseDTO() {
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        offlineUser.setId(userId);
        UpdatedStatusUserResponseDTO expectedUpdatedStatusUserResponseDTO =
                UserTestDataFactory.buildOnlineUpdatedStatusUserResponseDTO(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(offlineUser));

        ResponseEntity<UpdatedStatusUserResponseDTO> response =
                userService.setUserStatus(userId, onlineUpdateStatusUserRequestDTO);
        UpdatedStatusUserResponseDTO responseBody = response.getBody();

        assertNotNull(response);
        assertNotNull(responseBody);
        assertEquals(expectedHttpStatus, response.getStatusCode());
        assertEquals(expectedUpdatedStatusUserResponseDTO, responseBody);
        verify(userRepository).findById(userId);
    }


    @Test
    void setUserStatus_WhenUserIdIsValidAndUserStatusIsCorrectAndUserHasOnlineStatus_ThrowsBadRequestException() {
        String expectedExceptionMessage = String.format("User with id: %s already has status: %s",
                userId, onlineUpdateStatusUserRequestDTO.getStatus());
        when(userRepository.findById(userId)).thenReturn(Optional.of(onlineUser));


        BadRequestException badRequestException =
                assertThrows(BadRequestException.class,
                        () -> userService.setUserStatus(userId, onlineUpdateStatusUserRequestDTO));

        assertNotNull(badRequestException);
        assertEquals(expectedExceptionMessage, badRequestException.getMessage());
        verify(userRepository).findById(userId);
    }

    @Test
    void setUserStatus_WhenUserIdIsValidAndUserStatusIsCorrectAndUserHasOfflineStatus_ThrowsBadRequestException() {
        String expectedExceptionMessage = String.format("User with id: %s already has status: %s",
                userId, offlineUpdateStatusUserRequestDTO.getStatus());
        when(userRepository.findById(userId)).thenReturn(Optional.of(offlineUser));


        BadRequestException badRequestException =
                assertThrows(BadRequestException.class,
                        () -> userService.setUserStatus(userId, offlineUpdateStatusUserRequestDTO));

        assertNotNull(badRequestException);
        assertEquals(expectedExceptionMessage, badRequestException.getMessage());
        verify(userRepository).findById(userId);
    }

    @Test
    void setUserStatus_WhenUserIdIsValidAndUserStatusIsIncorrect_ThrowsBadRequestException() {
        String expectedExceptionMessage = String.format("Wrong user status: %s",
                incorrectUpdateStatusUserRequestDTO.getStatus());

        BadRequestException badRequestException =
                assertThrows(BadRequestException.class,
                        () -> userService.setUserStatus(userId, incorrectUpdateStatusUserRequestDTO));

        assertNotNull(badRequestException);
        assertEquals(expectedExceptionMessage, badRequestException.getMessage());
        verify(userRepository, never()).findById(userId);
    }

    @Test
    void setUserStatus_WhenUserStatusIsCorrectAndUserIdIsInvalidAnd_ThrowsNotFoundException() {
        String expectedExceptionMessage = new NotFoundException(User.class, userId).getMessage();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());


        NotFoundException notFoundException =
                assertThrows(NotFoundException.class,
                        () -> userService.setUserStatus(userId, onlineUpdateStatusUserRequestDTO));

        assertNotNull(notFoundException);
        assertEquals(expectedExceptionMessage, notFoundException.getMessage());
        verify(userRepository).findById(userId);
    }
}
