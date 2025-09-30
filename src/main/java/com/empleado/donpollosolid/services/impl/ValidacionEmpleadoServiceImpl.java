package com.empleado.donpollosolid.services.impl;

import com.empleado.donpollosolid.dtos.EmpleadoCreateDto;
import com.empleado.donpollosolid.dtos.EmpleadoUpdateDto;
import com.empleado.donpollosolid.repositories.EmpleadoRepository;
import com.empleado.donpollosolid.services.ValidacionEmpleadoService;
import org.springframework.stereotype.Service;

/**
 * SRP: agrupa SOLO reglas de negocio/validación para el agregado Empleado.
 * OCP: nuevas reglas se agregan aquí sin tocar el flujo de EmpleadoServiceImpl.
 * DIP: EmpleadoServiceImpl depende de esta abstracción para no mezclar responsabilidades.
 */
@Service
public class ValidacionEmpleadoServiceImpl implements ValidacionEmpleadoService {

    private final EmpleadoRepository repo;

    public ValidacionEmpleadoServiceImpl(EmpleadoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void validarCrear(EmpleadoCreateDto dto) {
        // Reglas simples de dominio (puedes extender con Strategy si crece):
        if (dto == null) throw new IllegalArgumentException("Datos requeridos");
        if (isBlank(dto.getNombre()))          throw new IllegalArgumentException("El nombre es obligatorio");
        if (isBlank(dto.getIdentificacion()))  throw new IllegalArgumentException("La identificación es obligatoria");
        if (isBlank(dto.getCargo()))           throw new IllegalArgumentException("El cargo es obligatorio");

        // Unicidad de identificación (regla crítica del negocio)
        if (repo.existsByIdentificacion(dto.getIdentificacion())) {
            throw new IllegalArgumentException("La identificación ya existe");
        }
    }

    @Override
    public void validarActualizar(String id, EmpleadoUpdateDto dto) {
        if (isBlank(id)) throw new IllegalArgumentException("Id obligatorio");
        if (dto == null) throw new IllegalArgumentException("Datos requeridos");

        // Ejemplos de reglas de actualización:
        // - Nombre/cargo no vacíos si vienen
        if (dto.getNombre() != null && isBlank(dto.getNombre())) {
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        }
        if (dto.getCargo() != null && isBlank(dto.getCargo())) {
            throw new IllegalArgumentException("El cargo no puede ser vacío");
        }

        // (Si se habilitara cambio de identificación, aquí validarías unicidad)
        // if (dto.getIdentificacion()!=null) {...}
    }

    @Override
    public void validarEliminar(String id) {
        if (isBlank(id)) throw new IllegalArgumentException("Id obligatorio");
        // Aquí podrías validar restricciones (ej: no eliminar si tiene nómina abierta, etc.)
    }

    // Utilidad local
    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}
