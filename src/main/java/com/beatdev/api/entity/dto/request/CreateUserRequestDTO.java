package com.beatdev.api.entity.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

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
    @JsonProperty(value = "username", access = JsonProperty.Access.WRITE_ONLY)
    private String username;

    @NotBlank(message = "Password can't be null")
    @Length(min = 6, max = 255, message = "The password must be between 6 and 255 characters.")
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private String password;

    @NotBlank(message = "Email can't be null")
    @Email(message = "Please fill the correct email")
    @JsonProperty(value = "email", access = JsonProperty.Access.WRITE_ONLY)
    private String email;

    @NotBlank(message = "Image link can't be null")
    @Length(max = 2000, message = "Image link too long. Max length is 2000")
    @JsonProperty(value = "image_link", access = JsonProperty.Access.WRITE_ONLY)
    private String imageLink;
}
