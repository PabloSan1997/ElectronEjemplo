package com.tareas.servicios.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tareas.servicios.models.dto.ErrorDto;
import com.tareas.servicios.models.dto.TokenResponseDto;
import com.tareas.servicios.security.JWTMethods;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static com.tareas.servicios.security.SecurityProperties.*;
import java.io.IOException;
import java.util.Collection;

public class ValidationTokenFilter extends BasicAuthenticationFilter {
    public ValidationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(AUTHORIZATION);
        if(header == null || !header.startsWith(BEARER)){
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(BEARER, "");
        JWTMethods jwtMethods = new JWTMethods();
        try{
            TokenResponseDto tokenResponseDto = jwtMethods.validateToken(token);
            String username = tokenResponseDto.getUsername();
            Collection<? extends GrantedAuthority> authorities = tokenResponseDto.getAuthorities();
            request.setAttribute("username", username);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        }catch (Exception e){
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ErrorDto errorDto = ErrorDto.builder()
                    .error(status.getReasonPhrase())
                    .message(e.getMessage())
                    .statusCode(status.value()).build();
            String error = new ObjectMapper().writeValueAsString(errorDto);

            response.setStatus(status.value());
            response.setContentType(APPLICATION_JSON);
            response.setCharacterEncoding(UTF8);
            response.getWriter().write(error);
        }
    }
}
