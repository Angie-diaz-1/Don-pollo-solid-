package com.empleado.donpollosolid.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * SRP: Solo define los datos mínimos para crear un empleado.
 * ISP: Evita campos innecesarios que pertenecen a otros casos de uso.
 */
@Getter
@Setter
public class EmpleadoCreateDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;

    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;

    // Materiales opcionales al crear
    private MaterialesDto materiales;

    }
