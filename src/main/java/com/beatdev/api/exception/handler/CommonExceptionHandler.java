package com.beatdev.api.exception.handler;

import com.beatdev.api.exception.BadRequestException;
import com.beatdev.api.exception.MapperException;
import com.beatdev.api.exception.NotFoundException;
import com.beatdev.api.exception.dto.ExceptionResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * The CommonExceptionHandler class for handling exceptions.
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> badRequestExceptionHandler(BadRequestException exception) {

        log.info("Handle BadRequestException: {}", exception.getMessage());

        return new ResponseEntity<>(ExceptionResponseDTO.builder()
                .time(LocalDateTime.now())
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> notFoundExceptionHandler(NotFoundException exception) {

        log.info("Handle NotFoundException: {}", exception.getMessage());

        return new ResponseEntity<>(ExceptionResponseDTO.builder()
                .time(LocalDateTime.now())
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> mapperExceptionHandler(MapperException exception) {

        log.info("Handle NotFoundException: {}", exception.getMessage());

        return new ResponseEntity<>(ExceptionResponseDTO.builder()
                .time(LocalDateTime.now())
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception) {

        log.info("Handle NotFoundException: {}", exception.getMessage());

        return new ResponseEntity<>(ExceptionResponseDTO.builder()
                .time(LocalDateTime.now())
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
    }
}
