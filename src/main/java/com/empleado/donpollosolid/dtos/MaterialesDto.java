package com.empleado.donpollosolid.dtos;

/**
 * SRP: Este DTO solo transporta datos de materiales.
 * Reutilizable en create/update/view sin mezclar lógica de dominio.
 */
public class MaterialesDto {
    private Integer cantidadVacunas;
    private Integer cantidadAlimento;
    private Integer cantidadProductosAseo;
    private Integer cantidadPesasAvicolas;

    public Integer getCantidadVacunas() { return cantidadVacunas; }
    public void setCantidadVacunas(Integer cantidadVacunas) { this.cantidadVacunas = cantidadVacunas; }

    public Integer getCantidadAlimento() { return cantidadAlimento; }
    public void setCantidadAlimento(Integer cantidadAlimento) { this.cantidadAlimento = cantidadAlimento; }

    public Integer getCantidadProductosAseo() { return cantidadProductosAseo; }
    public void setCantidadProductosAseo(Integer cantidadProductosAseo) { this.cantidadProductosAseo = cantidadProductosAseo; }

    public Integer getCantidadPesasAvicolas() { return cantidadPesasAvicolas; }
    public void setCantidadPesasAvicolas(Integer cantidadPesasAvicolas) { this.cantidadPesasAvicolas = cantidadPesasAvicolas; }
}
