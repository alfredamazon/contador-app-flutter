package com.example.contadorapp.service;

import com.example.contadorapp.entity.Empleado;
import com.example.contadorapp.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repo;

    public EmpleadoService(EmpleadoRepository repo) {
        this.repo = repo;
    }

    public List<Empleado> findAll() {
        return repo.findAll();
    }

    public Empleado save(Empleado e) {
        return repo.save(e);
    }
}