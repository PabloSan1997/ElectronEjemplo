package com.tareas.servicios.services;

import com.tareas.servicios.models.UserEntity;
import com.tareas.servicios.models.dto.RegisterDto;

public interface UserService {
    UserEntity register(RegisterDto registerDto);
    void delete(String username);
}
