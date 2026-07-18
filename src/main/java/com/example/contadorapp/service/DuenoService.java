package com.example.contadorapp.service;

import com.example.contadorapp.entity.Dueno;
import com.example.contadorapp.repository.DuenoRepository;
import org.springframework.stereotype.Service;
import com.example.contadorapp.entity.Dueno;
import java.util.List;
import com.example.contadorapp.dto.ResumenEmpleadoDTO;
import com.example.contadorapp.dto.LoginResponseDTO;
import com.example.contadorapp.entity.Empleado;
import com.example.contadorapp.repository.EmpleadoRepository;
@Service
public class DuenoService {


    private final DuenoRepository repo;
    private final EmpleadoRepository empleadoRepository;
    public DuenoService(DuenoRepository repo, EmpleadoRepository empleadoRepository) {
        this.repo = repo;
        this.empleadoRepository = empleadoRepository;
    }


    public List<Dueno> findAll() {
        return repo.findAll();
    }

    public Dueno save(Dueno d) {
        return repo.save(d);
    }
    public List<ResumenEmpleadoDTO> getResumenHoy(Long duenoId) {

    List<Object[]> data = repo.getResumenHoy(duenoId);

    return data.stream()
            .map(row -> new ResumenEmpleadoDTO(
                    ((Number) row[0]).longValue(),
                    (String) row[1],
                    ((Number) row[2]).longValue(),
                    row[3] != null ? ((Number) row[3]).doubleValue() : 0.0
            ))
            .toList();
    }
    public LoginResponseDTO login(String nombre, String password) {

        Dueno d = repo.findByNombreAndPassword(nombre, password)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        Empleado emp = empleadoRepository.findFirstByDuenoId(d.getId())
                .orElseThrow(() -> new RuntimeException("Sin empleado"));

        return new LoginResponseDTO(
                d.getId(),
                emp.getId(),
                d.getNombre()
        );
    }
}
