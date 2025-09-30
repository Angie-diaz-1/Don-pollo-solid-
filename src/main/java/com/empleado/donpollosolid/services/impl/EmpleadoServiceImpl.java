package com.empleado.donpollosolid.services.impl;

import com.empleado.donpollosolid.dtos.EmpleadoCreateDto;
import com.empleado.donpollosolid.dtos.EmpleadoUpdateDto;
import com.empleado.donpollosolid.dtos.EmpleadoViewDto;
import com.empleado.donpollosolid.mapping.EmpleadoMapper;
import com.empleado.donpollosolid.models.Empleado;
import com.empleado.donpollosolid.repositories.EmpleadoRepository;
import com.empleado.donpollosolid.services.EmpleadoService;
import com.empleado.donpollosolid.services.ValidacionEmpleadoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * SRP: Esta clase orquesta casos de uso de Empleado (crear, actualizar, eliminar, consultar).
 * No persiste directamente (repo), no mapea (mapper), no valida reglas complejas (ValidacionEmpleadoService).
 * DIP: Depende de las abstracciones del repositorio y del validador, no de detalles concretos.
 */
@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository repo;                 // DIP
    private final ValidacionEmpleadoService validador;     // DIP

    public EmpleadoServiceImpl(EmpleadoRepository repo, ValidacionEmpleadoService validador) {
        this.repo = repo;
        this.validador = validador;
    }

    @Override
    public EmpleadoViewDto crear(EmpleadoCreateDto dto) {
        // SRP: delego validaciones de negocio al servicio de validación
        validador.validarCrear(dto);

        // SRP/OCP: mapeo aislado; si cambian campos/DTOs se extiende el mapper
        Empleado nuevo = EmpleadoMapper.toModel(dto);

        // LSP: cualquier implementación de repo (JPA/InMemory) respeta el contrato save/find
        Empleado guardado = repo.save(nuevo);
        return EmpleadoMapper.toView(guardado);
    }

    @Override
    public EmpleadoViewDto actualizar(String id, EmpleadoUpdateDto dto) {
        validador.validarActualizar(id, dto);

        Empleado existente = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        // OCP: update parcial vía merge; agregar campos no rompe el flujo
        EmpleadoMapper.merge(existente, dto);

        Empleado actualizado = repo.save(existente);
        return EmpleadoMapper.toView(actualizado);
    }

    @Override
    public void eliminar(String id) {
        validador.validarEliminar(id);
        if (!repo.existsById(id)) throw new IllegalArgumentException("Empleado no encontrado");
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadoViewDto> obtenerPorId(String id) {
        // SRP: sólo orquesto; mapping aparte
        return repo.findById(id).map(EmpleadoMapper::toView);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoViewDto> listar() {
        return repo.findAll().stream().map(EmpleadoMapper::toView).toList();
    }
}
