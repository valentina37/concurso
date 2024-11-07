package com.carros.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carros.app.entity.vehiculo;

public interface VehiculoRepository extends MongoRepository<vehiculo, String> {
}

