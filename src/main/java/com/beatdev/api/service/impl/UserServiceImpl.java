package com.beatdev.api.service.impl;

import com.beatdev.api.entity.User;
import com.beatdev.api.entity.UserStatus;
import com.beatdev.api.entity.dto.request.CreateUserRequestDTO;
import com.beatdev.api.entity.dto.request.UpdateStatusUserRequestDTO;
import com.beatdev.api.entity.dto.response.CreatedUserResponseDTO;
import com.beatdev.api.entity.dto.response.UpdatedStatusUserResponseDTO;
import com.beatdev.api.entity.dto.response.UserInfoResponseDTO;
import com.beatdev.api.entity.mapper.UserMapper;
import com.beatdev.api.exception.BadRequestException;
import com.beatdev.api.exception.NotFoundException;
import com.beatdev.api.repository.UserRepository;
import com.beatdev.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * UserService implementation.
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public ResponseEntity<CreatedUserResponseDTO> create(CreateUserRequestDTO createUserRequestDTO) {

        log.info("Creating new user: {}", createUserRequestDTO);

        String username = createUserRequestDTO.getUsername();
        String email = createUserRequestDTO.getEmail();

        if (userRepository.findByUsername(username).isPresent()) {

            log.info("BadRequest: There is user with specified username: {}", username);

            throw new BadRequestException(String.format("There is user with specified username: %s", username));
        }

        if (userRepository.findByEmail(email).isPresent()) {

            log.info("BadRequest: There is user with specified email: {}", email);

            throw new BadRequestException(String.format("There is user with specified email: %s", email));
        }

        User user = userMapper.createUserRequestDTOToUser(createUserRequestDTO);
        user.setStatus(UserStatus.ONLINE);

        CreatedUserResponseDTO createdUserResponseDTO = userMapper
                .userToCreatedUserResponseDTO(userRepository.save(user));

        log.info("User: {} was created", user);

        return new ResponseEntity<>(createdUserResponseDTO, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UserInfoResponseDTO> findById(UUID uuid) {

        log.info("Finding user by id: {}", uuid);

        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(User.class, uuid));

        UserInfoResponseDTO userInfoResponseDTO = userMapper.userToUserInfoResponseDTO(user);

        log.info("Find user: {}", userInfoResponseDTO);

        return new ResponseEntity<>(userInfoResponseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UpdatedStatusUserResponseDTO> setUserStatus(UUID uuid,
                                                                      UpdateStatusUserRequestDTO userRequestDTO) {
        String userRequestDTOStatus = userRequestDTO.getStatus();
        UserStatus previousUserStatus;
        UserStatus requestUserStatus;

        log.info("Updating user status to: {} by user's id: {}", userRequestDTOStatus, uuid);

        try {
            requestUserStatus = UserStatus.valueOf(userRequestDTOStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(String.format("Wrong user status: %s", userRequestDTOStatus));
        }

        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(User.class, uuid));

        previousUserStatus = user.getStatus();

        if (user.getStatus().equals(requestUserStatus)) {
            throw new BadRequestException(String.format("User with id: %s already has status: %s",
                    uuid, requestUserStatus));
        }

        user.setStatus(requestUserStatus);

        UpdatedStatusUserResponseDTO updatedStatusUserResponseDTO =
                userMapper.userToUpdatedStatusUserResponseDTO(user);
        updatedStatusUserResponseDTO.setPreviousStatus(previousUserStatus);

        log.info("Update user status: {}", updatedStatusUserResponseDTO);

        return new ResponseEntity<>(updatedStatusUserResponseDTO, HttpStatus.OK);
    }
}

