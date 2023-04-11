package com.beatdev.api.entity.dto.response;

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
public class CreatedUserResponseDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;
}
