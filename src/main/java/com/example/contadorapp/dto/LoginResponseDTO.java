package com.example.contadorapp.dto;

public class LoginResponseDTO {

    private Long id;
    private Long empleadoId;
    private String nombre;

    public LoginResponseDTO() {
        // constructor vacío (buena práctica JSON)
    }

    public LoginResponseDTO(Long id, Long empleadoId, String nombre) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}