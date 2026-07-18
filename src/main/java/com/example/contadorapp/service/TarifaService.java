package com.example.contadorapp.service;

import org.springframework.stereotype.Service;

@Service
public class TarifaService {

    public static final double TARIFA_PASAJE = 12.0;

    public double obtenerTarifa() {
        return TARIFA_PASAJE;
    }
}