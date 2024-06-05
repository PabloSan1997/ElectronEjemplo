package com.tareas.servicios.security;


import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class SecurityProperties {
    public static final String BEARER = "Bearer ";
    public static final String APPLICATION_JSON = "application/json";
    public static final String AUTHORIZATION = "Authorization";
    public static final String UTF8 = "utf-8";
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
}