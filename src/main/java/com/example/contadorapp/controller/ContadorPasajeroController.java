package com.example.contadorapp.controller;

import com.example.contadorapp.entity.ContadorPasajero;
import com.example.contadorapp.service.ContadorPasajeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/contadores")
public class ContadorPasajeroController {

    private final ContadorPasajeroService service;

    public ContadorPasajeroController(ContadorPasajeroService service) {
        this.service = service;
    }

    @GetMapping
    public List<ContadorPasajero> getAll() {
        return service.findAll();
    }

    @PostMapping("/empleado/{empleadoId}/pulso")
    public ContadorPasajero pulso(@PathVariable Long empleadoId) {
        return service.pulso(empleadoId);
    }

    @GetMapping("/empleado/{empleadoId}")
    public ContadorPasajero getHoy(@PathVariable Long empleadoId) {
        return service.findByFecha(empleadoId, LocalDate.now());
    }

    @GetMapping("/empleado/{empleadoId}/fecha/{fecha}")
    public ContadorPasajero getByFecha(
            @PathVariable Long empleadoId,
            @PathVariable String fecha
    ) {
        return service.findByFecha(empleadoId, LocalDate.parse(fecha));
    }

    // 🔴 BOTÓN FLUTTER (PISADA DINÁMICA)
    @PostMapping("/pisadas/add/{empleadoId}")
    public ResponseEntity<ContadorPasajero> addPisada(@PathVariable Long empleadoId) {

        ContadorPasajero result = service.pulso(empleadoId);

        return ResponseEntity.ok(result); // 🔴 IMPORTANTE: ya NO "OK"
    }
}