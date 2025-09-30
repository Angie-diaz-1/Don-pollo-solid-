package com.empleado.donpollosolid.controllers;

import com.empleado.donpollosolid.dtos.EmpleadoCreateDto;
import com.empleado.donpollosolid.dtos.EmpleadoUpdateDto;
import com.empleado.donpollosolid.dtos.EmpleadoViewDto;
import com.empleado.donpollosolid.services.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * ISP: controlador enfocado a MUTACIONES (POST/PUT/DELETE) para rol Admin.
 * SRP: solo coordina entrada/salida HTTP; la lógica vive en el service.
 * DIP: depende de la interfaz EmpleadoService para mantener bajo acoplamiento.
 */
@RestController
@RequestMapping("/api/admin/empleados")
public class EmpleadoAdminController {

    private final EmpleadoService service;

    public EmpleadoAdminController(EmpleadoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpleadoViewDto crear(@Valid @RequestBody EmpleadoCreateDto dto) {
        // SRP: validación básica por anotaciones y delego reglas al service
        return service.crear(dto);
    }

    @PutMapping("/{id}")
    public EmpleadoViewDto actualizar(@PathVariable String id, @RequestBody EmpleadoUpdateDto dto) {
        // OCP: soporta updates parciales mediante merge en el mapper
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String id) {
        service.eliminar(id);
    }
}
