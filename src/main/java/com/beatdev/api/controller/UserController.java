package com.beatdev.api.controller;

import com.beatdev.api.entity.dto.request.CreateUserRequestDTO;
import com.beatdev.api.entity.dto.request.UpdateStatusUserRequestDTO;
import com.beatdev.api.entity.dto.response.CreatedUserResponseDTO;
import com.beatdev.api.entity.dto.response.UpdatedStatusUserResponseDTO;
import com.beatdev.api.entity.dto.response.UserInfoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import javax.validation.Valid;

/**
 * Interface that presents basic endpoints for working with User entity.
 */
@RequestMapping("/users")
@Api(tags = "User Controller", description = "User management controller")
public interface UserController {

    @Operation(
            summary = "Create new user",
            description = "This endpoint allows you to create a new user in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreatedUserResponseDTO.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = {@Content(schema = @Schema())})
    })
    @PostMapping
    ResponseEntity<CreatedUserResponseDTO> create(@ApiParam(value = "DTO for create new user", required = true)
                                                  @RequestBody @Valid CreateUserRequestDTO createUserRequestDTO);

    @Operation(summary = "Find user",
            description = "Find user by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Found the following user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserInfoResponseDTO.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Bed request",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404",
                    description = "Not found",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{id}")
    ResponseEntity<UserInfoResponseDTO> findUserById(@ApiParam(value = "User id to return", required = true)
                                                     @PathVariable(name = "id") UUID uuid);

    @Operation(summary = "Update user status",
            description = "Update user status by params in dto object")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "User status successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserInfoResponseDTO.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Bed request",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404",
                    description = "Not found",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = {@Content(schema = @Schema())})
    })
    @PatchMapping("/{id}/status")
    ResponseEntity<UpdatedStatusUserResponseDTO> updateUserStatus(
            @ApiParam(value = "User id to return", required = true)
            @PathVariable(name = "id") UUID uuid,
            @ApiParam(value = "DTO for update user status", required = true)
            @RequestBody
            @Valid UpdateStatusUserRequestDTO userRequestDTO);


}
