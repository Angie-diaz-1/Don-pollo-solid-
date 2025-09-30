package com.empleado.donpollosolid.controllers;

import com.empleado.donpollosolid.dtos.EmpleadoViewDto;
import com.empleado.donpollosolid.services.EmpleadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ISP: controlador enfocado SOLO a consultas (GET).
 * SRP: expone endpoints de lectura; no orquesta validaciones ni persistencia.
 * DIP: depende de la abstracción EmpleadoService.
 */
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoQueryController {

    private final EmpleadoService service;

    public EmpleadoQueryController(EmpleadoService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmpleadoViewDto> listar() {
        // SRP: delego la lógica al servicio
        return service.listar();
    }

    @GetMapping("/{id}")
    public EmpleadoViewDto obtener(@PathVariable String id) {
        // LSP: el service retorna Optional; aquí decidimos respuesta estándar
        Optional<EmpleadoViewDto> res = service.obtenerPorId(id);
        return res.orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
    }
}
