package com.tareas.servicios.models.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private String error;
    private String message;
    private Integer statusCode;
}
