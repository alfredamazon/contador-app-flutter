package com.example.contadorapp.service;

import com.example.contadorapp.entity.ContadorPasajero;
import com.example.contadorapp.entity.Empleado;
import com.example.contadorapp.repository.ContadorPasajeroRepository;
import com.example.contadorapp.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContadorPasajeroService {

    private final ContadorPasajeroRepository repo;
    private final EmpleadoRepository empleadoRepository;
    private final TarifaService tarifaService;

    public ContadorPasajeroService(
            ContadorPasajeroRepository repo,
            EmpleadoRepository empleadoRepository,
            TarifaService tarifaService
    ) {
        this.repo = repo;
        this.empleadoRepository = empleadoRepository;
        this.tarifaService = tarifaService;
    }

    public List<ContadorPasajero> findAll() {
        return repo.findAll();
    }

    // 🔴 INICIO IMPLEMENTACIÓN REAL
    public ContadorPasajero pulso(Long empleadoId) {

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() ->
                        new RuntimeException("Empleado no existe: " + empleadoId));

        LocalDate hoy = LocalDate.now();

        ContadorPasajero contador = repo
                .findByEmpleadoIdAndFecha(empleadoId, hoy)
                .orElseGet(() -> {

                    ContadorPasajero nuevo = new ContadorPasajero();

                    nuevo.setFecha(hoy);
                    nuevo.setEmpleado(empleado);

                    nuevo.setTotalContado(0L);
                    nuevo.setIngresoReal(0.0);
                    nuevo.setIngresoEsperado(0);

                    nuevo.setUltimaActualizacion(LocalDateTime.now());

                    return nuevo;
                });

        // 🔴 INCREMENTO SEGURO (EVITA NULL)
        contador.setTotalContado(
                (contador.getTotalContado() == null ? 0L : contador.getTotalContado()) + 1
        );

        // 🔴 INGRESO DINÁMICO
        contador.setIngresoReal(
                (contador.getIngresoReal() == null ? 0.0 : contador.getIngresoReal())
                        + tarifaService.obtenerTarifa()
        );

        contador.setUltimaActualizacion(LocalDateTime.now());

        return repo.save(contador);
    }
    // 🔴 FIN IMPLEMENTACIÓN REAL

    public ContadorPasajero findByFecha(Long empleadoId, LocalDate fecha) {
        return repo.findByEmpleadoIdAndFecha(empleadoId, fecha)
                .orElseThrow(() ->
                        new RuntimeException("No existe registro para esa fecha"));
    }
}