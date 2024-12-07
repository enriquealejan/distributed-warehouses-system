package com.empresa.gestion_almacen.repositories;

import com.empresa.gestion_almacen.models.Almacen;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlmacenRepository extends MongoRepository<Almacen, String> {
    // Métodos personalizados (opcional)
    Almacen findByNombre(String nombre);
}

