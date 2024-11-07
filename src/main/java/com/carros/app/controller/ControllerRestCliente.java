package com.carros.app.controller;

import com.carros.app.entity.cliente;
import com.carros.app.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ControllerRestCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public cliente crearCliente(@RequestBody cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public cliente actualizarCliente(@PathVariable String id, @RequestBody cliente clienteActualizado) {
        clienteActualizado.setId(id);
        return clienteRepository.save(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable String id) {
        clienteRepository.deleteById(id);
    }
}
