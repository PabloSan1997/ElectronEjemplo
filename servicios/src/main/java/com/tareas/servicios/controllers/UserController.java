package com.tareas.servicios.controllers;

import com.tareas.servicios.models.dto.RegisterDto;
import com.tareas.servicios.services.UserService;
import com.tareas.servicios.validation.GenerateValidationError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private GenerateValidationError generateValidationError;
    @PostMapping("/register")
    public ResponseEntity<?> save(@Valid @RequestBody RegisterDto registerDto, BindingResult result){
        if(result.hasFieldErrors()) generateValidationError.generate(result);
        var res = userService.register(registerDto);
        return ResponseEntity.status(201).body(res);
    }
}
