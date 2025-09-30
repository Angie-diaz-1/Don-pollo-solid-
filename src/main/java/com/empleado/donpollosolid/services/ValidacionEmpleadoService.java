package com.empleado.donpollosolid.services;

import com.empleado.donpollosolid.dtos.EmpleadoCreateDto;
import com.empleado.donpollosolid.dtos.EmpleadoUpdateDto;

/**
 * SRP: agrupa reglas de negocio/validación del dominio de Empleado.
 * OCP: añadir nuevas reglas = nuevas implementaciones/métodos sin tocar el flujo principal.
 * DIP: el servicio principal puede depender de esta abstracción para validar.
 */
public interface ValidacionEmpleadoService {

    /** Reglas previas a crear (unicidad de identificación, formatos, etc.). */
    void validarCrear(EmpleadoCreateDto dto);

    /** Reglas previas a actualizar (existencia, cambios válidos, etc.). */
    void validarActualizar(String id, EmpleadoUpdateDto dto);

    /** Reglas previas a eliminar (invariantes, restricciones, etc.). */
    void validarEliminar(String id);
}
