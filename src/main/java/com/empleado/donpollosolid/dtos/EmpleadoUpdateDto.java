package com.empleado.donpollosolid.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * SRP: Solo datos actualizables.
 * OCP: Si mañana agregamos otro campo editable, lo extendemos aquí sin tocar el flujo del servicio.
 */
@Getter
@Setter
public class EmpleadoUpdateDto {

    private String nombre;          // opcional
    private String cargo;           // opcional
    private MaterialesDto materiales; // opcional

    }
