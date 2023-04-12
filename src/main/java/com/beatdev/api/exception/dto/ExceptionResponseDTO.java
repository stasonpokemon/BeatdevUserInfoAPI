package com.beatdev.api.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class presents a DTO, which is available via CommonExceptionHandler.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponseDTO {

    private LocalDateTime time;

    private Object message;

}
