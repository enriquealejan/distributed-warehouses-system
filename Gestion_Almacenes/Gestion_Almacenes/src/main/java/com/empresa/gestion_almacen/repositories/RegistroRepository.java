package com.empresa.gestion_almacen.repositories;

import com.empresa.gestion_almacen.models.Registro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RegistroRepository extends MongoRepository<Registro, String> {
    // Métodos personalizados (opcional)
    List<Registro> findByProductoId(String productoId);
}


