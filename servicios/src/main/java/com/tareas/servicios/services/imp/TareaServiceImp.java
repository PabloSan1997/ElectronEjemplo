package com.tareas.servicios.services.imp;

import com.tareas.servicios.Repository.TareaRepository;
import com.tareas.servicios.Repository.UserRepository;
import com.tareas.servicios.exceptions.MyBadRequestException;
import com.tareas.servicios.models.Tarea;
import com.tareas.servicios.models.UserEntity;
import com.tareas.servicios.models.dto.TareaDto;
import com.tareas.servicios.models.dto.TareaEstadoDto;
import com.tareas.servicios.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TareaServiceImp implements TareaService {
    @Autowired
    private TareaRepository tareaRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<Tarea> findAll(String username) {
        return tareaRepository.findTareas(username);
    }

    @Override
    @Transactional
    public Tarea save(TareaDto tareaDto, String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()->{
            throw new MyBadRequestException("No se encontró usuario");
        });
        Tarea tarea = Tarea.builder()
                .tarea(tareaDto.getTarea()).user(user).build();
        return tareaRepository.save(tarea);
    }

    @Override
    @Transactional
    public Tarea edit(Long id, TareaEstadoDto tareaEstadoDto, String username) {
       Tarea tarea = findByUsernameAndId(username, id);
        tarea.setEstado(tareaEstadoDto.getEstado());
        return tareaRepository.save(tarea);
    }

    @Override
    @Transactional
    public void delete(Long id, String username) {
        Tarea tarea =  findByUsernameAndId(username, id);
        tareaRepository.deleteById(tarea.getId());
    }

    @Transactional
    private Tarea findByUsernameAndId(String username, Long id){
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()->{
            throw new MyBadRequestException("No se encontró usuario");
        });
        return tareaRepository.findOneTarea(user.getUsername(), id).orElseThrow(()->{
            throw new MyBadRequestException("No se encontró tarea");
        });
    }

}
