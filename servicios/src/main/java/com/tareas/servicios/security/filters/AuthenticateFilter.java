package com.tareas.servicios.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tareas.servicios.exceptions.MyBadRequestException;
import com.tareas.servicios.models.dto.ErrorDto;
import com.tareas.servicios.models.dto.LoginResponseDto;
import com.tareas.servicios.models.dto.RegisterDto;
import com.tareas.servicios.security.JWTMethods;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.tareas.servicios.security.SecurityProperties.*;

import java.io.IOException;

public class AuthenticateFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AuthenticateFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = "";
        String password = "";
        try {
            RegisterDto registerDto = new ObjectMapper().readValue(request.getInputStream(), RegisterDto.class);
            username = registerDto.getUsername();
            password = registerDto.getPassword();
        } catch (IOException e) {
            throw new MyBadRequestException(e.getMessage());

        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        JWTMethods jwtMethods = new JWTMethods();
        String username = user.getUsername();
        try {
            String token = jwtMethods.generateToken(username, user.getAuthorities());
            LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                    .username(username).token(token).build();

            response.setContentType(APPLICATION_JSON);
            response.setStatus(HttpStatus.OK.value());
            response.setCharacterEncoding(UTF8);
            response.setHeader("token", BEARER + token);
            response.getWriter().write(new ObjectMapper().writeValueAsString(loginResponseDto));
        } catch (Exception e) {
            throw new MyBadRequestException(e.getMessage());
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDto errorDto = ErrorDto.builder()
                .error(status.getReasonPhrase())
                .statusCode(status.value())
                .message("Usuario o contraseña incorrectos").build();

        response.setContentType(APPLICATION_JSON);
        response.setStatus(status.value());
        response.setCharacterEncoding(UTF8);
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorDto));
    }
}
