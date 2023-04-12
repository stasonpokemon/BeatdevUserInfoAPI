package com.beatdev.api.entity.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

import static com.beatdev.api.util.swagger.OpenApiConstants.USER_ONLINE_STATUS;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_STATUS_DESCRIPTION;

/**
 * This class presents a DTO, which is available via UserController endpoints.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateStatusUserRequestDTO {

    @NotBlank(message = "Status can't be null")
    @Schema(example = USER_ONLINE_STATUS, description = USER_STATUS_DESCRIPTION)
    @JsonProperty(value = "status", access = JsonProperty.Access.READ_WRITE)
    private String status;
}
