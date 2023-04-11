package com.beatdev.api.entity.dto.request;

import com.beatdev.api.entity.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

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
    @JsonProperty(value = "status", access = JsonProperty.Access.WRITE_ONLY)
    private String status;
}
