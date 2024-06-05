package com.tareas.servicios.validation;

import com.tareas.servicios.exceptions.MyBadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class GenerateValidationError {
    public void generate(BindingResult result) {
        List<FieldError> errors = result.getFieldErrors();
        StringBuilder message = new StringBuilder();
        for (FieldError err : errors) {
            message.append(err.getField()).append(" ").append(err.getDefaultMessage()).append(" ");
        }
        throw new MyBadRequestException(message.toString());
    }
}
