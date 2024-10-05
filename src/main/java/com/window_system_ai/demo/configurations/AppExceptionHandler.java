package com.window_system_ai.demo.configurations;

import com.window_system_ai.demo.dtos.ExceptionDTO;
import com.window_system_ai.demo.dtos.GenericResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

    private static final String EXCEPTION_LOG_MESSAGE = "Encountered an exception: {}; Caused By: {}";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<GenericResponseDTO> handleGenericError(final Exception i) {
        log.error(EXCEPTION_LOG_MESSAGE, i.getMessage(), i.getCause(), i);
        return new ResponseEntity<>(GenericResponseDTO.build(new ExceptionDTO().buildGenericError()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}