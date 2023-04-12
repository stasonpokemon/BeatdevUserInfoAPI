package com.beatdev.api.entity.dto.response;

import com.beatdev.api.entity.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.beatdev.api.util.swagger.OpenApiConstants.USER_EMAIL;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_EMAIL_DESCRIPTION;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_IMAGE_LINK;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_IMAGE_LINK_DESCRIPTION;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_ONLINE_STATUS;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_STATUS_DESCRIPTION;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_USERNAME;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_USERNAME_DESCRIPTION;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_UUID;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_UUID_DESCRIPTION;

/**
 * This class presents a DTO, which is available via UserController endpoints.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponseDTO {

    @Schema(example = USER_UUID, description = USER_UUID_DESCRIPTION)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @Schema(example = USER_USERNAME, description = USER_USERNAME_DESCRIPTION)
    @JsonProperty(value = "username", access = JsonProperty.Access.READ_ONLY)
    private String username;

    @Schema(example = USER_EMAIL, description = USER_EMAIL_DESCRIPTION)
    @JsonProperty(value = "email", access = JsonProperty.Access.READ_ONLY)
    private String email;

    @Schema(example = USER_IMAGE_LINK, description = USER_IMAGE_LINK_DESCRIPTION)
    @JsonProperty(value = "image_link", access = JsonProperty.Access.READ_ONLY)
    private String imageLink;

    @Schema(example = USER_ONLINE_STATUS, description = USER_STATUS_DESCRIPTION)
    @JsonProperty(value = "status", access = JsonProperty.Access.READ_ONLY)
    private UserStatus status;
}
