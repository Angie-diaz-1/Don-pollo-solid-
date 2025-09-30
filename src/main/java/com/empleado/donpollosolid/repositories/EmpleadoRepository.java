package com.empleado.donpollosolid.repositories;

import com.empleado.donpollosolid.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
    boolean existsByIdentificacion(String identificacion);
    Optional<Empleado> findByIdentificacion(String identificacion);
}
