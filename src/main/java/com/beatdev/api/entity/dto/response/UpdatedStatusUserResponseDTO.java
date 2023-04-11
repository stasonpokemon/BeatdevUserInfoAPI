package com.beatdev.api.entity.dto.response;

import com.beatdev.api.entity.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.beatdev.api.util.swagger.OpenApiConstants.USER_OFFLINE_STATUS;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_ONLINE_STATUS;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_STATUS_DESCRIPTION;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_UUID;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_UUID_DESCRIPTION;

/**
 * This class presents a DTO, which is available via UserController endpoints.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatedStatusUserResponseDTO {

    @Schema(example = USER_UUID, description = USER_UUID_DESCRIPTION)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @Schema(example = USER_ONLINE_STATUS, description = USER_STATUS_DESCRIPTION)
    @JsonProperty(value = "previous_status", access = JsonProperty.Access.READ_ONLY)
    private UserStatus previousStatus;

    @Schema(example = USER_OFFLINE_STATUS, description = USER_STATUS_DESCRIPTION)
    @JsonProperty(value = "current_status", access = JsonProperty.Access.READ_ONLY)
    private UserStatus currentStatus;
}
