package com.example.contadorapp.dto;

import lombok.Data;
@Data
public class DuenoRequestDTO {

    private String nombre;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;
    private String contrasena;

    // getters y setters
}