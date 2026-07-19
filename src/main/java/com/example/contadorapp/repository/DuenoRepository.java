package com.example.contadorapp.repository;

import com.example.contadorapp.entity.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface DuenoRepository extends JpaRepository<Dueno, Long> {
    Optional<Dueno> findByNombreAndPassword(
            String nombre,
            String password);
                @Query(value = """
                SELECT 
                    e.id AS empleadoId,
                    e.nombre AS nombre,
                    cp.total_contado AS totalPasajes,
                    cp.ingreso_real AS ingreso
                FROM contador_pasajero cp
                JOIN empleado e 
                ON cp.empleado_id = e.id
                WHERE e.dueno_id = :duenoId
                AND cp.fecha = CURRENT_DATE
                """, nativeQuery = true)
                List<Object[]> getResumenHoy(@Param("duenoId") Long duenoId);
}
