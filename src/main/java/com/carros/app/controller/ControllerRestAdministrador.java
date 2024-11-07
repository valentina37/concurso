package com.carros.app.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.carros.app.entity.administrador;
import com.carros.app.exception.NotFoundException;
import com.carros.app.repository.AdministradorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ControllerRestAdministrador {
	@Autowired
	private AdministradorRepository administradorRepository;

	@GetMapping("/")
	public List<administrador> getAllAsociacions() {
		return administradorRepository.findAll();

	}

	@GetMapping("/{id}")
	public administrador getAdministradorById(@PathVariable String id) {
		return administradorRepository.findById(id).orElseThrow(() -> new NotFoundException("Administrdor no encontrado"));
	}

	@PostMapping("/")
	public administrador saveAdministrador(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		administrador asociacion = mapper.convertValue(body, administrador.class);
		return administradorRepository.save(asociacion);
	}

	@PutMapping("/{id}")
	public administrador updateAdministrador(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		administrador asociacion = mapper.convertValue(body, administrador.class);
		asociacion.setId(id);
		return administradorRepository.save(deleteAdministrador(null));
	}

	@DeleteMapping("/{id}")
	public administrador deleteAdministrador(@PathVariable String id) {
		administrador administrador = administradorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Administrador no encontrado"));
		administradorRepository.deleteById(id);
		return administrador;
	}
}