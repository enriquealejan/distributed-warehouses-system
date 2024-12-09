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
    public String enviarObtenerAlmacenes() {
        sender.sendMessage("almacen-get-queue", "Obtener almacenes");
        return "Solicitud de obtención de almacenes enviada a la cola.";
    }

    @PostMapping
    public String enviarCrearAlmacen(@RequestBody Almacen almacen) {
        sender.sendMessage("almacen-post-queue", almacen);
        return "Solicitud de creación de almacén enviada a la cola.";
    }

    @PutMapping("/{id}")
    public String enviarActualizarAlmacen(@PathVariable String id, @RequestBody Almacen almacen) {
        almacen.setId(id);
        sender.sendMessage("almacen-put-queue", almacen);
        return "Solicitud de actualización de almacén enviada a la cola.";
    }

    @DeleteMapping("/{id}")
    public String enviarEliminarAlmacen(@PathVariable String id) {
        sender.sendMessage("almacen-delete-queue", id);
        return "Solicitud de eliminación de almacén enviada a la cola.";
    }
}

