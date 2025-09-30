package com.empleado.donpollosolid.mapping;

import com.empleado.donpollosolid.dtos.*;
import com.empleado.donpollosolid.models.*;

/**
 * SRP: Esta clase SOLO mapea Empleado <-> DTOs (no valida, no persiste).
 * OCP: Si agregamos campos nuevos, extendemos aquí sin tocar casos de uso.
 */
public final class EmpleadoMapper {

    private EmpleadoMapper() {}

    /** Crear entidad desde DTO de creación. */
    public static Empleado toModel(EmpleadoCreateDto dto) {
        // DIP: Reutilizamos otro mapper en lugar de acoplar EmpleadoMapper a detalles de Materiales.
        Materiales materiales = MaterialesMapper.toModel(dto.getMateriales());
        return new Empleado(dto.getNombre(), dto.getIdentificacion(), dto.getCargo(), materiales);
    }

    /** Actualización parcial (PATCH/PUT controlado) sobre entidad existente. */
    public static void merge(Empleado target, EmpleadoUpdateDto dto) {
        if (target == null || dto == null) return;

        // SRP: sólo asignaciones; reglas/validaciones complejas van en servicios/validadores.
        if (dto.getNombre() != null) target.setNombre(dto.getNombre());
        if (dto.getCargo()  != null) target.setCargo(dto.getCargo());

        // Garantizamos objeto embebido
        if (target.getMateriales() == null) target.setMateriales(new Materiales());

        // OCP/ISP: delegamos el merge del embebido al mapper específico.
        MaterialesMapper.merge(target.getMateriales(), dto.getMateriales());
    }

    /** Vista de salida consolidada hacia el cliente. */
    public static EmpleadoViewDto toView(Empleado e) {
        if (e == null) return null;
        EmpleadoViewDto v = new EmpleadoViewDto();
        v.setId(e.getId());
        v.setNombre(e.getNombre());
        v.setIdentificacion(e.getIdentificacion());
        v.setCargo(e.getCargo());
        v.setTotalMateriales(e.getTotalMateriales()); // valor derivado (no lógica aquí)
        v.setMateriales(MaterialesMapper.toDto(e.getMateriales()));
        return v;
    }
}
