package com.tareas.servicios.services.imp;

import com.tareas.servicios.Repository.UserRepository;
import com.tareas.servicios.exceptions.MyBadRequestException;
import com.tareas.servicios.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new MyBadRequestException("No se encontró usuario o contraseña");
        });
        Collection<? extends GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(user.getRole())
        );
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getEnable(),
                true,
                true,
                true,
                authorities
        );
    }
}
