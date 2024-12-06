package com.empresa.gestion_almacen.repositories;

import com.empresa.gestion_almacen.models.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
    List<Registro> findByProductoId(Long productoId);
}

