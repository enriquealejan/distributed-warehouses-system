package com.empresa.gestion_almacen.controllers;

import com.empresa.gestion_almacen.models.Almacen;
import com.empresa.gestion_almacen.repositories.AlmacenRepository;
import com.empresa.gestion_almacen.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
public class AlmacenController {

    @Autowired
    private Sender sender; // Servicio que envía mensajes a las colas

    @GetMapping
    public List<Almacen> obtenerAlmacenes() {
        Object response = sender.sendAndReceive("almacen-get-queue", "procesarObtenerAlmacenes");
        if(response instanceof List<?>) {
            return (List<Almacen>) response;
        }
        throw new RuntimeException("Error al obtener almacenes");
    }

    @PostMapping
    public List<Almacen> crearAlmacen(@RequestBody Almacen almacen) {
        Object response = sender.sendAndReceive("almacen-post-queue", almacen);
        if(response instanceof List<?>) {
            return (List<Almacen>) response;
        }
        throw new RuntimeException("Error al crear almacén");
    }

    @PutMapping("/{id}")
    public List<Almacen> actualizarAlmacen(@PathVariable String id, @RequestBody Almacen almacen) {
        almacen.setId(id);
        Object response = sender.sendAndReceive("almacen-put-queue", almacen);
        if(response instanceof List<?>) {
            return (List<Almacen>) response;
        }
        throw new RuntimeException("Error al actualizar almacén");
    }

    @DeleteMapping("/{id}")
    public List<Almacen> eliminarAlmacen(@PathVariable String id) {
        Object response = sender.sendAndReceive("almacen-delete-queue", id);
        if(response instanceof List<?>) {
            return (List<Almacen>) response;
        }
        throw new RuntimeException("Error al eliminar almacén");
    }
}

