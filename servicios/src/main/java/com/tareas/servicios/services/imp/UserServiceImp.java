package com.tareas.servicios.services.imp;

import com.tareas.servicios.Repository.UserRepository;
import com.tareas.servicios.exceptions.MyBadRequestException;
import com.tareas.servicios.models.UserEntity;
import com.tareas.servicios.models.dto.RegisterDto;
import com.tareas.servicios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public UserEntity register(RegisterDto registerDto) {
        Optional<UserEntity> oUser = userRepository.findByUsername(registerDto.getUsername());
        if(oUser.isPresent())
            throw new MyBadRequestException("Nombre de usuario existente");

        String password = passwordEncoder.encode(registerDto.getPassword());

        UserEntity user = UserEntity.builder()
                .username(registerDto.getUsername())
                .password(password).build();

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()->{
            throw new MyBadRequestException("No se encontro usuario a borrar");
        });
        user.setEnable(false);
        userRepository.save(user);
    }
}
