package com.empresa.gestion_almacen.repositories;

import com.empresa.gestion_almacen.models.Almacen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlmacenRepository extends MongoRepository<Almacen, String> {
    // Métodos personalizados (opcional)
    Almacen findByNombre(String nombre);
}

