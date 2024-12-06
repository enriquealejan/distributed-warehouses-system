package com.empresa.gestion_almacen.repositories;

import com.empresa.gestion_almacen.models.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
}
