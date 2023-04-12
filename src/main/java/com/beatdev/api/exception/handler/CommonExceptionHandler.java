package com.beatdev.api.exception.handler;

import com.beatdev.api.exception.BadRequestException;
import com.beatdev.api.exception.MapperException;
import com.beatdev.api.exception.NotFoundException;
import com.beatdev.api.exception.dto.ExceptionResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .message(exception.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> notFoundExceptionHandler(NotFoundException exception) {

        log.info("Handle NotFoundException: {}", exception.getMessage());

        return new ResponseEntity<>(ExceptionResponseDTO.builder()
                .time(LocalDateTime.now())
                .message(exception.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> mapperExceptionHandler(MapperException exception) {

        log.info("Handle MapperException: {}", exception.getMessage());

        return new ResponseEntity<>(ExceptionResponseDTO.builder()
                .time(LocalDateTime.now())
                .message(exception.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception) {

        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        log.info("Handle MethodArgumentNotValidException: {} with message: {}",
                exception.getObjectName(), errors);

        return new ResponseEntity<>(ExceptionResponseDTO.builder()
                .time(LocalDateTime.now())
                .message(errors).build(), HttpStatus.BAD_REQUEST);
    }
}
