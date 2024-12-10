package com.empresa.gestion_almacen.controllers;

import com.empresa.gestion_almacen.models.Registro;
import com.empresa.gestion_almacen.models.Producto;
import com.empresa.gestion_almacen.repositories.RegistroRepository;
import com.empresa.gestion_almacen.repositories.ProductoRepository;
import com.empresa.gestion_almacen.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {

    @Autowired
    private Sender sender; // Servicio que envía mensajes a las colas

    @GetMapping
    public String enviarObtenerRegistros() {
        sender.sendAndReceive("registro-get-queue", "Obtener registros");
        return "Solicitud para obtener registros enviada a la cola.";
    }

    @PostMapping
    public String enviarCrearRegistro(@RequestBody Registro registro) {
        sender.sendAndReceive("registro-post-queue", registro);
        return "Solicitud de creación de registro enviada a la cola.";
    }

    @PutMapping("/{id}")
    public String enviarActualizarRegistro(@PathVariable String id, @RequestBody Registro registro) {
        registro.setId(id);
        sender.sendAndReceive("registro-put-queue", registro);
        return "Solicitud de actualización de registro enviada a la cola.";
    }

    @DeleteMapping("/{id}")
    public String enviarEliminarRegistro(@PathVariable Long id) {
        sender.sendAndReceive("registro-delete-queue", id);
        return "Solicitud de eliminación de registro enviada a la cola.";
    }
}


