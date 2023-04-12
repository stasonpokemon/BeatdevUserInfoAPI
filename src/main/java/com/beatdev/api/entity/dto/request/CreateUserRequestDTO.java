package com.beatdev.api.entity.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.beatdev.api.util.swagger.OpenApiConstants.USER_EMAIL;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_EMAIL_DESCRIPTION;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_IMAGE_LINK;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_IMAGE_LINK_DESCRIPTION;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_PASSWORD;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_PASSWORD_DESCRIPTION;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_USERNAME;
import static com.beatdev.api.util.swagger.OpenApiConstants.USER_USERNAME_DESCRIPTION;

/**
 * This class presents a DTO, which is available via UserController endpoints.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateUserRequestDTO {

    @NotBlank(message = "Username can't be null")
    @Length(min = 3, max = 255, message = "The username must be between 3 and 255 characters.")
    @Schema(example = USER_USERNAME, description = USER_USERNAME_DESCRIPTION)
    @JsonProperty(value = "username", access = JsonProperty.Access.WRITE_ONLY)
    private String username;

    @NotBlank(message = "Password can't be null")
    @Length(min = 6, max = 255, message = "The password must be between 6 and 255 characters.")
    @Schema(example = USER_PASSWORD, description = USER_PASSWORD_DESCRIPTION)
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private String password;

    @NotBlank(message = "Email can't be null")
    @Email(message = "Please fill the correct email")
    @Schema(example = USER_EMAIL, description = USER_EMAIL_DESCRIPTION)
    @JsonProperty(value = "email", access = JsonProperty.Access.WRITE_ONLY)
    private String email;

    @NotBlank(message = "Image link can't be null")
    @Length(max = 2000, message = "Image link too long. Max length is 2000")
    @Schema(example = USER_IMAGE_LINK, description = USER_IMAGE_LINK_DESCRIPTION)
    @JsonProperty(value = "image_link", access = JsonProperty.Access.WRITE_ONLY)
    private String imageLink;
}
