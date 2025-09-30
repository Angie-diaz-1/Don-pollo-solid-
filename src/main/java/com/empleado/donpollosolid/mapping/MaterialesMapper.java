package com.empleado.donpollosolid.mapping;

import com.empleado.donpollosolid.dtos.MaterialesDto;
import com.empleado.donpollosolid.models.Materiales;

/**
 * SRP: Esta clase SOLO se encarga de mapear entre Materiales y MaterialesDto.
 * OCP: Si mañana agregamos un nuevo campo a Materiales, extendemos aquí sin tocar servicios.
 */
public final class MaterialesMapper {

    private MaterialesMapper() {}

    public static Materiales toModel(MaterialesDto dto) {
        if (dto == null) return new Materiales();
        Materiales m = new Materiales();
        materialesValidator(dto, m);
        return m;
    }

    private static void materialesValidator(MaterialesDto dto, Materiales m) {
        if (dto.getCantidadVacunas() != null)      m.setCantidadVacunas(dto.getCantidadVacunas());
        if (dto.getCantidadAlimento() != null)     m.setCantidadAlimento(dto.getCantidadAlimento());
        if (dto.getCantidadProductosAseo() != null)m.setCantidadProductosAseo(dto.getCantidadProductosAseo());
        if (dto.getCantidadPesasAvicolas() != null)m.setCantidadPesasAvicolas(dto.getCantidadPesasAvicolas());
    }

    public static MaterialesDto toDto(Materiales m) {
        if (m == null) return null;
        MaterialesDto dto = new MaterialesDto();
        dto.setCantidadVacunas(m.getCantidadVacunas());
        dto.setCantidadAlimento(m.getCantidadAlimento());
        dto.setCantidadProductosAseo(m.getCantidadProductosAseo());
        dto.setCantidadPesasAvicolas(m.getCantidadPesasAvicolas());
        return dto;
    }

    /**
     * ISP/OCP: Actualiza sólo lo que llega en el DTO (parcial).
     * Mantener merge aquí evita mezclar responsabilidades en el servicio.
     */
    public static void merge(Materiales target, MaterialesDto dto) {
        if (target == null || dto == null) return;
        materialesValidator(dto, target);
    }
}
