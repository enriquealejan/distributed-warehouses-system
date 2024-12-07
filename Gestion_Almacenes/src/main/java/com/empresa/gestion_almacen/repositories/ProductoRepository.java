package com.empresa.gestion_almacen.repositories;

import com.empresa.gestion_almacen.models.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepository extends MongoRepository<Producto, String> {
    // Puedes agregar métodos personalizados si lo necesitas
    Producto findByNombre(String nombre);
}
