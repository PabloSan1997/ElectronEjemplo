package com.tareas.servicios.controllers;


import com.tareas.servicios.exceptions.MyBadRequestException;
import com.tareas.servicios.models.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    private ErrorDto generateError(HttpStatus status, Exception e){
        return ErrorDto.builder()
                .statusCode(status.value())
                .error(status.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler({
            MyBadRequestException.class
    })
    public ResponseEntity<?> badRequest(Exception e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(generateError(status, e));
    }
}
