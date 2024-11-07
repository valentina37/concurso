package com.carros.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carros.app.entity.administrador;

public interface AdministradorRepository extends MongoRepository<administrador, String> {

}
