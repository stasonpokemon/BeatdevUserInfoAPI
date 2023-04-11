package com.beatdev.api.entity.dto.response;

import com.beatdev.api.entity.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

/**
 * This class presents a DTO, which is available via UserController endpoints.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponseDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "username", access = JsonProperty.Access.READ_ONLY)
    private String username;

    @JsonProperty(value = "email", access = JsonProperty.Access.READ_ONLY)
    private String email;

    @JsonProperty(value = "image_link", access = JsonProperty.Access.READ_ONLY)
    private String imageLink;

    @JsonProperty(value = "status", access = JsonProperty.Access.READ_ONLY)
    private UserStatus status;
}
