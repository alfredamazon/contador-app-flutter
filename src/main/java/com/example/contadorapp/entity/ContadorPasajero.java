package com.example.contadorapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contador_pasajero")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContadorPasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 número de pasajeros contados
    private Long totalContado;

    // 🔥 dinero generado (totalContado * tarifa)
    private Double ingresoReal;

    // 🔥 meta opcional del día
    private Integer ingresoEsperado;

    // 🔥 día del registro (clave del sistema)
    private LocalDate fecha;

    // 🔥 última actualización del pulso
    private LocalDateTime ultimaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "dueno"})
    private Empleado empleado;

    // 🟢 FIX IMPORTANTE: evita nulls en creación JPA
    @PrePersist
    public void prePersist() {

        if (totalContado == null) {
            totalContado = 0L;
        }

        if (ingresoReal == null) {
            ingresoReal = 0.0;
        }

        if (ingresoEsperado == null) {
            ingresoEsperado = 0;
        }

        if (fecha == null) {
            fecha = LocalDate.now();
        }

        if (ultimaActualizacion == null) {
            ultimaActualizacion = LocalDateTime.now();
        }
    }
}