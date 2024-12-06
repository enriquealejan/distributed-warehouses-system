package com.empresa.gestion_almacen.repositories;

import com.empresa.gestion_almacen.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Métodos adicionales si son necesarios (consultas personalizadas)
}