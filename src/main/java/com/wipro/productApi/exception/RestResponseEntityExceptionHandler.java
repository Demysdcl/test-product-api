package com.wipro.productApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExpection.class)
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundExpection expection, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new StandartError(
                        System.currentTimeMillis(),
                        HttpStatus.NOT_FOUND.value(),
                        "Não encontrado",
                        expection.getMessage(),
                        request.getRequestURI()
                ));
    }

}
