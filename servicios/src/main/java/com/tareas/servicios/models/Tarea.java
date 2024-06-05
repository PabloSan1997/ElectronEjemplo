package com.tareas.servicios.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150, nullable = false)
    private String tarea;
    private Boolean estado;
    @Column(name = "create_at")
    private Date createAt;
    @PrePersist
    public void prePersist() {
        estado = false;
        createAt = new Date();
    }
    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private UserEntity user;
}
