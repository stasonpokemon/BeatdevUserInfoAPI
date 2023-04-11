package com.beatdev.api.util;

import com.beatdev.api.entity.User;
import com.beatdev.api.entity.UserStatus;
import com.beatdev.api.entity.dto.request.CreateUserRequestDTO;
import com.beatdev.api.entity.dto.response.UpdatedStatusUserResponseDTO;
import com.beatdev.api.entity.dto.response.UserInfoResponseDTO;
import com.beatdev.api.entity.mapper.UserMapper;
import com.beatdev.api.exception.NotFoundException;
import com.beatdev.api.exception.dto.ExceptionResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * UserTestDataFactory factory test class for create user entities and DTOs.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTestDataFactory {

    private static final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public static User buildUserOnlineUser() {
        return User.builder()
                .username("testusername")
                .password("testpassword")
                .email("testemail.gmail.com")
                .imageLink("https://avatars.mds.yandex.net/i?id=aa3e10c6aa0fbcd2d0face272ca6d10cc6fb5f59" +
                        "-8498011-images-thumbs&n=13")
                .status(UserStatus.ONLINE).build();
    }

    public static User buildUserOfflineUser() {
        return User.builder()
                .username("testusername")
                .password("testpassword")
                .email("testemail.gmail.com")
                .imageLink("https://avatars.mds.yandex.net/i?id=aa3e10c6aa0fbcd2d0face272ca6d10cc6fb5f59" +
                        "-8498011-images-thumbs&n=13")
                .status(UserStatus.OFFLINE).build();
    }

    public static User buildUserForCreateFromCreateUserRequestDTO(CreateUserRequestDTO createUserRequestDTO) {
        User user = userMapper.createUserRequestDTOToUser(createUserRequestDTO);
        user.setStatus(UserStatus.ONLINE);
        return user;
    }

    public static User buildUserFromUserAndId(User user, UUID uuid) {
        return User.builder()
                .id(uuid)
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .imageLink(user.getImageLink())
                .status(user.getStatus()).build();
    }

    public static CreateUserRequestDTO buildCreateUserRequestDTO() {
        return CreateUserRequestDTO.builder()
                .username("testusername")
                .password("testpassword")
                .email("testemail.gmail.com")
                .imageLink("https://avatars.mds.yandex.net/i?id=aa3e10c6aa0fbcd2d0face272ca6d10cc6fb5f59" +
                        "-8498011-images-thumbs&n=13").build();
    }

    public static UpdatedStatusUserResponseDTO buildOfflineUpdatedStatusUserResponseDTO(UUID uuid) {
        return UpdatedStatusUserResponseDTO.builder()
                .id(uuid)
                .currentStatus(UserStatus.OFFLINE)
                .previousStatus(UserStatus.ONLINE).build();
    }

    public static UpdatedStatusUserResponseDTO buildOnlineUpdatedStatusUserResponseDTO(UUID uuid) {
        return UpdatedStatusUserResponseDTO.builder()
                .id(uuid)
                .currentStatus(UserStatus.ONLINE)
                .previousStatus(UserStatus.OFFLINE).build();
    }

    public static UserInfoResponseDTO buildUserInfoResponseDTO() {
        return UserInfoResponseDTO.builder()
                .id(UUID.randomUUID())
                .username("testusername")
                .email("testemail@icloud.com")
                .imageLink("https://avatars.mds.yandex.net/i?id=aa3e10c6aa0fbcd2d0face272ca6d10cc6fb5f59" +
                        "-8498011-images-thumbs&n=13")
                .status(UserStatus.ONLINE).build();
    }

    public static ExceptionResponseDTO buildNotFoundExceptionResponseDTO(NotFoundException exception){
        return ExceptionResponseDTO.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .time(LocalDateTime.now()).build();
    }
}
