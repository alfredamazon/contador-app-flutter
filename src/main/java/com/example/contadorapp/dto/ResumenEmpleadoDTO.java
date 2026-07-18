package com.example.contadorapp.dto;

public class ResumenEmpleadoDTO {

    private Long empleadoId;
    private String nombre;
    private Long totalPasajes;
    private Double ingreso;

    public ResumenEmpleadoDTO(Long empleadoId, String nombre, Long totalPasajes, Double ingreso) {
        this.empleadoId = empleadoId;
        this.nombre = nombre;
        this.totalPasajes = totalPasajes;
        this.ingreso = ingreso;
    }

    public Long getEmpleadoId() { return empleadoId; }
    public String getNombre() { return nombre; }
    public Long getTotalPasajes() { return totalPasajes; }
    public Double getIngreso() { return ingreso; }
}