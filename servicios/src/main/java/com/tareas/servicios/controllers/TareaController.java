package com.tareas.servicios.controllers;

import com.tareas.servicios.models.dto.TareaDto;
import com.tareas.servicios.models.dto.TareaEstadoDto;
import com.tareas.servicios.services.TareaService;
import com.tareas.servicios.validation.GenerateValidationError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    @Autowired
    private GenerateValidationError generateValidationError;
    @Autowired
    private TareaService tareaService;

    @GetMapping("")
    public ResponseEntity<?> findAll(@RequestAttribute String username) {
        return ResponseEntity.ok().body(tareaService.findAll(username));
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody TareaDto tareaDto, BindingResult result, @RequestAttribute String username) {
        if (result.hasFieldErrors()) generateValidationError.generate(result);
        var res = tareaService.save(tareaDto, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@Valid @RequestBody TareaEstadoDto tareaEstadoDto, BindingResult result, @RequestAttribute String username, @PathVariable Long id) {
        if (result.hasFieldErrors()) generateValidationError.generate(result);
        var res = tareaService.edit(id, tareaEstadoDto, username);
        return ResponseEntity.ok().body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestAttribute String username, @PathVariable Long id) {
        tareaService.delete(id, username);
        return ResponseEntity.noContent().build();
    }
}
