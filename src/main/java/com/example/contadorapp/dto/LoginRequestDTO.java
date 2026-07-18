package com.example.contadorapp.dto;

public class LoginRequestDTO {

    private String nombre;
    private String password;

    public LoginRequestDTO() {
        // constructor vacío requerido por Spring
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}