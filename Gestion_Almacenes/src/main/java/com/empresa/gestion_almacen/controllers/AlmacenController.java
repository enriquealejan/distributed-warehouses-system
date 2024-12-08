package com.empresa.gestion_almacen.controllers;

import com.empresa.gestion_almacen.models.Almacen;
import com.empresa.gestion_almacen.repositories.AlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes") // Ruta base para los endpoints relacionados con almacenes
public class AlmacenController {
    @Autowired
    private AlmacenRepository almacenRepository;

    // 1. Obtener todos los almacenes
    @GetMapping
    public List<Almacen> getAllAlmacenes() {
        return almacenRepository.findAll();
    }

    // 2. Crear un nuevo almacén
    @PostMapping
    public Almacen createAlmacen(@RequestBody Almacen almacen) {
        return almacenRepository.save(almacen);
    }

    // 3. Obtener un almacén por su ID
    @GetMapping("/{id}")
    public Almacen getAlmacenById(@PathVariable String id) {
        return almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado con ID: " + id));
    }

    // 4. Actualizar un almacén existente
    @PutMapping("/{id}")
    public Almacen updateAlmacen(@PathVariable String id, @RequestBody Almacen almacenDetalles) {
        Almacen almacen = almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado con ID: " + id));

        almacen.setNombre(almacenDetalles.getNombre());
        almacen.setUbicacion(almacenDetalles.getUbicacion());
        return almacenRepository.save(almacen);
    }

    // 5. Eliminar un almacén
    @DeleteMapping("/{id}")
    public void deleteAlmacen(@PathVariable String id) {
        Almacen almacen = almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado con ID: " + id));
        almacenRepository.delete(almacen);
    }

}
