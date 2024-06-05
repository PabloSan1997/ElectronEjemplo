package com.tareas.servicios.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tareas.servicios.models.dto.TokenResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.tareas.servicios.security.SecurityProperties.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public class JWTMethods {
    public String generateToken(String username, Collection<? extends GrantedAuthority> authoritiesG) throws JsonProcessingException {
        String  authorities = new ObjectMapper().writeValueAsString(authoritiesG);
        Claims claims = Jwts.claims().add("authorities", authorities).build();
        return Jwts
                .builder()
                .subject(username)
                .claims(claims)
                .signWith(SECRET_KEY)
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .issuedAt(new Date())
                .compact();
    }
    public TokenResponseDto validateToken(String token) throws IOException {
        Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build()
                .parseSignedClaims(token).getPayload();
        String username = claims.getSubject();
        Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                new ObjectMapper()
                        .addMixIn(SimpleGrantedAuthority.class, GrantedAuthorityJson.class)
                        .readValue(claims.get("authorities").toString().getBytes(), SimpleGrantedAuthority[].class));
        return TokenResponseDto.builder()
                .username(username).authorities(authorities).build();
    }
}
