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
                    COUNT(cp.id) AS totalPasajes,
                    SUM(cp.ingreso_real) AS ingreso
                FROM contador_pasajero cp
                JOIN empleado e ON cp.empleado_id = e.id
                WHERE e.dueno_id = :duenoId
                AND cp.fecha_pisada >= CURRENT_DATE
                AND cp.fecha_pisada < CURRENT_DATE + INTERVAL '1 day'
                GROUP BY e.id, e.nombre
                """, nativeQuery = true)
            List<Object[]> getResumenHoy(@Param("duenoId") Long duenoId);
}
