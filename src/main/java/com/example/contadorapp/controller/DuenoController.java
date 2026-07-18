package com.example.contadorapp.controller;

import com.example.contadorapp.entity.Dueno;
import com.example.contadorapp.entity.Empleado;
import com.example.contadorapp.service.DuenoService;
import com.example.contadorapp.repository.DuenoRepository;
import com.example.contadorapp.repository.EmpleadoRepository;

import com.example.contadorapp.dto.ResumenEmpleadoDTO;
import com.example.contadorapp.dto.LoginRequestDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/duenos")
public class DuenoController {

    private final DuenoService service;

    @Autowired
    private DuenoRepository duenoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public DuenoController(DuenoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Dueno> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Dueno create(@RequestBody Dueno d) {
        return service.save(d);
    }

    // 🔴 LOGIN CORREGIDO
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequestDTO req) {

    if (req.getNombre() == null || req.getPassword() == null) {
        return ResponseEntity
                .badRequest()
                .body("Nombre o password vacío");
    }

    Optional<Dueno> opt = duenoRepository
            .findByNombreAndPassword(req.getNombre(), req.getPassword());

    if (opt.isEmpty()) {
        return ResponseEntity
                .status(401)
                .body("Credenciales inválidas");
    }

    Dueno dueno = opt.get();

    Optional<Empleado> empOpt = empleadoRepository
            .findFirstByDuenoId(dueno.getId());

    Long empleadoId = empOpt.map(Empleado::getId).orElse(null);

    Map<String, Object> response = new HashMap<>();
    response.put("id", dueno.getId());
    response.put("nombre", dueno.getNombre());
    response.put("empleadoId", empleadoId);

    System.out.println("LOGIN RESPONSE = " + response);

    return ResponseEntity.ok(response);
}

    @GetMapping("/hoy/{duenoId}")
    public List<ResumenEmpleadoDTO> getHoy(@PathVariable Long duenoId) {
        return service.getResumenHoy(duenoId);
    }

    @GetMapping("/test")
    public String test() {
        return "BACKEND OK";
    }
    @GetMapping("/{duenoId}/empleados")
    public ResponseEntity<List<Empleado>> getEmpleados(@PathVariable Long duenoId) {

            List<Empleado> empleados = empleadoRepository.findByDuenoId(duenoId);

            return ResponseEntity.ok(empleados);
    }
}