package com.empleado.donpollosolid.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

/**
 * SRP: La entidad representa estado + invariantes simples.
 * No persiste ni mapea ni valida reglas complejas (eso va en repos, mappers y validadores).
 */
@Entity
@Getter
@Setter
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Hibernate 6 / Spring Boot 3
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String identificacion;

    @Column(nullable = false)
    private String cargo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "cantidadVacunas",        column = @Column(name = "cantidad_vacunas")),
            @AttributeOverride(name = "cantidadAlimento",       column = @Column(name = "cantidad_alimento")),
            @AttributeOverride(name = "cantidadProductosAseo",  column = @Column(name = "cantidad_productos_aseo")),
            @AttributeOverride(name = "cantidadPesasAvicolas",  column = @Column(name = "cantidad_pesas_avicolas"))
    })
    private Materiales materiales = new Materiales();

    protected Empleado() { /* JPA */ }

    public Empleado(String nombre, String identificacion, String cargo, Materiales materiales) {
        this.nombre = Objects.requireNonNull(nombre, "nombre obligatorio");
        this.identificacion = Objects.requireNonNull(identificacion, "identificación obligatoria");
        this.cargo = Objects.requireNonNull(cargo, "cargo obligatorio");
        this.materiales = (materiales == null) ? new Materiales() : materiales;
    }



    /** Conveniencia derivada del VO: respeta SRP dejando el cálculo en Materiales. */
    public int getTotalMateriales() {
        return (materiales == null) ? 0 : materiales.calcularTotalProductos();
    }
}
