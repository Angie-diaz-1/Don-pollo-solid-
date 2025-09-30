package com.empleado.donpollosolid.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * SRP: Solo encapsula datos y lógica propia de Materiales.
 */
@Embeddable
@Getter
@Setter
public class Materiales {
    private int cantidadVacunas;
    private int cantidadAlimento;
    private int cantidadProductosAseo;
    private int cantidadPesasAvicolas;

    public int calcularTotalProductos() {
        return cantidadVacunas + cantidadAlimento + cantidadProductosAseo + cantidadPesasAvicolas;
    }


}
