package com.empresa.gestion_almacen.repositories;

import com.empresa.gestion_almacen.models.Registro;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RegistroRepository extends MongoRepository<Registro, String> {
    // Métodos personalizados (opcional)
    List<Registro> findByProductoId(String productoId);

}


