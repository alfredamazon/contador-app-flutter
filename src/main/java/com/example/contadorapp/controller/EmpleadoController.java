package com.example.contadorapp.controller;

import com.example.contadorapp.entity.Dueno;
import com.example.contadorapp.entity.Empleado;
import com.example.contadorapp.repository.DuenoRepository;
import com.example.contadorapp.repository.EmpleadoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    private final EmpleadoRepository empleadoRepository;
    private final DuenoRepository duenoRepository;

    public EmpleadoController(
            EmpleadoRepository empleadoRepository,
            DuenoRepository duenoRepository
    ) {
        this.empleadoRepository = empleadoRepository;
        this.duenoRepository = duenoRepository;
    }

    // ===========================
    // OBTENER TODOS LOS EMPLEADOS
    // ===========================
    @GetMapping
    public List<Empleado> getAll() {
        return empleadoRepository.findAll();
    }

    // =====================================
    // OBTENER EMPLEADOS POR DUEÑO
    // =====================================
    @GetMapping("/{duenoId}/empleados")
    public List<Empleado> getEmpleados(@PathVariable Long duenoId) {
        return empleadoRepository.findByDuenoId(duenoId);
    }

    // =====================================
    // CREAR NUEVO EMPLEADO
    // =====================================
    @PostMapping
    public ResponseEntity<?> crearEmpleado(@RequestBody Empleado empleado) {

        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio.");
        }

        if (empleado.getDueno() == null || empleado.getDueno().getId() == null) {
            return ResponseEntity.badRequest().body("Debe indicar el dueño.");
        }

        Optional<Dueno> duenoOpt = duenoRepository.findById(
                empleado.getDueno().getId()
        );

        if (duenoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("El dueño no existe.");
        }

        empleado.setId(null);
        empleado.setDueno(duenoOpt.get());

        Empleado nuevoEmpleado = empleadoRepository.save(empleado);

        return ResponseEntity.ok(nuevoEmpleado);
    }
}
