package com.example.contadorapp.controller;

import com.example.contadorapp.entity.Empleado;
import com.example.contadorapp.entity.Dueno;
import com.example.contadorapp.repository.EmpleadoRepository;
import com.example.contadorapp.repository.DuenoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
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

    @GetMapping
    public List<Empleado> getAll() {
        return empleadoRepository.findAll();
    }
    @GetMapping("/{duenoId}/empleados")
    public List<Empleado> getEmpleados(@PathVariable Long duenoId) {
        return empleadoRepository.findByDuenoId(duenoId);
    }
}