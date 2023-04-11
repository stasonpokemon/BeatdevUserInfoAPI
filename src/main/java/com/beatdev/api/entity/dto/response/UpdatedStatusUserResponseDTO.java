package com.beatdev.api.entity.dto.response;

import com.beatdev.api.entity.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * This class presents a DTO, which is available via UserController endpoints.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatedStatusUserResponseDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "previous_status", access = JsonProperty.Access.READ_ONLY)
    private UserStatus previousStatus;

    @JsonProperty(value = "current_status", access = JsonProperty.Access.READ_ONLY)
    private UserStatus currentStatus;
}
