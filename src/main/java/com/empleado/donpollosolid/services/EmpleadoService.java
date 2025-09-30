package com.empleado.donpollosolid.services;

import com.empleado.donpollosolid.dtos.EmpleadoCreateDto;
import com.empleado.donpollosolid.dtos.EmpleadoUpdateDto;
import com.empleado.donpollosolid.dtos.EmpleadoViewDto;

import java.util.List;
import java.util.Optional;

/**
 * ISP: interfaz pequeña y enfocada a casos de uso del empleado.
 * DIP: los controladores dependen de esta abstracción, no de una clase concreta.
 */
public interface EmpleadoService {

    /** Crear un empleado nuevo desde un DTO de entrada. */
    EmpleadoViewDto crear(EmpleadoCreateDto dto);

    /** Actualizar parcialmente un empleado existente (PUT/PATCH controlado). */
    EmpleadoViewDto actualizar(String id, EmpleadoUpdateDto dto);

    /** Eliminar un empleado por id. */
    void eliminar(String id);

    /** Obtener un empleado por id. */
    Optional<EmpleadoViewDto> obtenerPorId(String id);

    /** Listar todos los empleados. */
    List<EmpleadoViewDto> listar();
}
