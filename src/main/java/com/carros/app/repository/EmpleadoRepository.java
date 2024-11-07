package com.carros.app.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.carros.app.entity.empleado;

public interface EmpleadoRepository extends MongoRepository<empleado, String> {

}