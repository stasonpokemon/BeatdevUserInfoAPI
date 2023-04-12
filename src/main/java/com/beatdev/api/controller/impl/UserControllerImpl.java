package com.beatdev.api.controller.impl;

import com.beatdev.api.controller.UserController;
import com.beatdev.api.entity.dto.request.CreateUserRequestDTO;
import com.beatdev.api.entity.dto.request.UpdateStatusUserRequestDTO;
import com.beatdev.api.entity.dto.response.CreatedUserResponseDTO;
import com.beatdev.api.entity.dto.response.UpdatedStatusUserResponseDTO;
import com.beatdev.api.entity.dto.response.UserInfoResponseDTO;
import com.beatdev.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Implementation class for UserController.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<CreatedUserResponseDTO> create(CreateUserRequestDTO createUserRequestDTO) {
        log.info("POST create method: start creating new user: {}", createUserRequestDTO);

        ResponseEntity<CreatedUserResponseDTO> response = userService.create(createUserRequestDTO);

        log.info("POST create method: successful creation of new user: {}",
                response.getBody());

        return response;
    }

    @Override
    public ResponseEntity<UserInfoResponseDTO> findUserById(UUID uuid) {

        log.info("GET findUserById method: start finding user by id: {}", uuid);

        ResponseEntity<UserInfoResponseDTO> response = userService.findById(uuid);

        log.info("GET findUserById method: successful user search: {}", response.getBody());

        return response;
    }

    @Override
    public ResponseEntity<UpdatedStatusUserResponseDTO> updateUserStatus(UUID uuid,
                                                                         UpdateStatusUserRequestDTO userRequestDTO) {

        log.info("PATCH setUserStatus method: start updating user status: {} for user with id: {}",
                userRequestDTO.getStatus(), uuid);

        ResponseEntity<UpdatedStatusUserResponseDTO> response =
                userService.setUserStatus(uuid, userRequestDTO);

        log.info("PATCH setUserStatus method: successful update status of user: {}",
                response.getBody());

        return response;
    }
}
