package com.tareas.servicios.Repository;

import com.tareas.servicios.models.Tarea;
import com.tareas.servicios.models.dto.TareaDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TareaRepository extends CrudRepository<Tarea, Long> {
    @Query("select t from Tarea t where t.user.username = ?1 order by t.id")
    List<Tarea> findTareas(String username);

    @Query("select t from Tarea t where t.user.username = ?1 and t.id = ?2")
    Optional<Tarea> findOneTarea(String username, Long id);
}
