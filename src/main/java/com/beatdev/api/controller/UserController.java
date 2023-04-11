package com.beatdev.api.controller;

import com.beatdev.api.entity.dto.request.CreateUserRequestDTO;
import com.beatdev.api.entity.dto.request.UpdateStatusUserRequestDTO;
import com.beatdev.api.entity.dto.response.CreatedUserResponseDTO;
import com.beatdev.api.entity.dto.response.UpdatedStatusUserResponseDTO;
import com.beatdev.api.entity.dto.response.UserInfoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Interface that presents basic endpoints for working with User entity.
 */
@RequestMapping("/users")
public interface UserController {

    @PostMapping
    ResponseEntity<CreatedUserResponseDTO> create(@RequestBody @Valid CreateUserRequestDTO createUserRequestDTO);

    @GetMapping("/{id}")
    ResponseEntity<UserInfoResponseDTO> findUserById(@PathVariable(name = "id") UUID uuid);

    @PatchMapping("/{id}/status")
    ResponseEntity<UpdatedStatusUserResponseDTO> updateUserStatus(@PathVariable(name = "id") UUID uuid,
                                                                  @RequestBody
                                                                  @Valid UpdateStatusUserRequestDTO userRequestDTO);


}
