package com.tareas.servicios.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GrantedAuthorityJson {
    @JsonCreator
    public GrantedAuthorityJson(@JsonProperty("authority") String role){}
}
