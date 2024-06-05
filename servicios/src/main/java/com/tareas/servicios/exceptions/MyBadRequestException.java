package com.tareas.servicios.exceptions;


public class MyBadRequestException extends RuntimeException{
    public MyBadRequestException(String message){
        super(message);
    }
}
