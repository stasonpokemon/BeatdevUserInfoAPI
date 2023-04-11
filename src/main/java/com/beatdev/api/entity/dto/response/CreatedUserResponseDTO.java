package com.beatdev.api.entity.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.beatdev.api.util.swagger.OpenApiConstants.USER_UUID;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_UUID_DESCRIPTION;

/**
 * This class presents a DTO, which is available via UserController endpoints.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatedUserResponseDTO {

    @Schema(example = USER_UUID, description = USER_UUID_DESCRIPTION)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;
}
