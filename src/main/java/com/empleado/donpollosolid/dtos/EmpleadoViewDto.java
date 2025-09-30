package com.empleado.donpollosolid.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * SRP: DTO de salida para mostrar datos consolidados al cliente.
 * Mantiene desacople entre la entidad JPA y la API pública (DIP).
 */
@Getter
@Setter
public class EmpleadoViewDto {

    private String id;
    private String nombre;
    private String identificacion;
    private String cargo;

    // Vista puede incluir totales derivados (no lógica, solo el valor calculado)
    private Integer totalMateriales;

    // También podemos exponer el detalle de materiales si lo necesitas en pantalla
    private MaterialesDto materiales;

   }
