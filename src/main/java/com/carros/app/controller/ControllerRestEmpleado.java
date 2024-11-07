package com.carros.app.controller;

import com.carros.app.entity.empleado;
import com.carros.app.repository.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class ControllerRestEmpleado {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public List<empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @PostMapping
    public empleado crearEmpleado(@RequestBody empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/{id}")
    public empleado actualizarEmpleado(@PathVariable String id, @RequestBody empleado empleadoActualizado) {
        empleadoActualizado.setId(id);
        return empleadoRepository.save(empleadoActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable String id) {
        empleadoRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public empleado obtenerEmpleadoPorId(@PathVariable String id) {
        return empleadoRepository.findById(id).orElse(null);
    }
}

