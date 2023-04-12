package com.beatdev.api.service;

import com.beatdev.api.entity.dto.request.CreateUserRequestDTO;
import com.beatdev.api.entity.dto.request.UpdateStatusUserRequestDTO;
import com.beatdev.api.entity.dto.response.CreatedUserResponseDTO;
import com.beatdev.api.entity.dto.response.UpdatedStatusUserResponseDTO;
import com.beatdev.api.entity.dto.response.UserInfoResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

/**
 * The UserService interface, which stores the business logic for working with the user.
 */
public interface UserService {

    ResponseEntity<CreatedUserResponseDTO> create(CreateUserRequestDTO createUserRequestDTO);

    ResponseEntity<UserInfoResponseDTO> findById(UUID uuid);

    ResponseEntity<UpdatedStatusUserResponseDTO> setUserStatus(UUID uuid, UpdateStatusUserRequestDTO userRequestDTO);
}
