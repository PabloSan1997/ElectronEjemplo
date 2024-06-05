package com.tareas.servicios.services;

import com.tareas.servicios.models.Tarea;
import com.tareas.servicios.models.dto.TareaDto;
import com.tareas.servicios.models.dto.TareaEstadoDto;

import java.util.List;

public interface TareaService {
    List<Tarea> findAll(String username);
    Tarea save( TareaDto tareaDto, String username);
    Tarea edit(Long id,TareaEstadoDto tareaEstadoDto, String username);
    void delete(Long id, String username);
}
