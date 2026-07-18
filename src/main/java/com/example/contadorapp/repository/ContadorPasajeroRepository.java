package com.example.contadorapp.repository;

import com.example.contadorapp.entity.ContadorPasajero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ContadorPasajeroRepository extends JpaRepository<ContadorPasajero, Long> {

    Optional<ContadorPasajero> findByEmpleadoIdAndFecha(Long empleadoId, LocalDate fecha);

}