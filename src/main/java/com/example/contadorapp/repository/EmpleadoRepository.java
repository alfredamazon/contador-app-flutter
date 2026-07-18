package com.example.contadorapp.repository;

import com.example.contadorapp.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByDuenoId(Long duenoId);
    Optional<Empleado> findFirstByDuenoId(Long duenoId);
}