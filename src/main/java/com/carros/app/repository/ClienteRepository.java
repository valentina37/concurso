package com.carros.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carros.app.entity.cliente;

public interface ClienteRepository extends MongoRepository<cliente, String> {
	
}
