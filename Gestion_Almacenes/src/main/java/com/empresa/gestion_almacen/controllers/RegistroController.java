package com.empresa.gestion_almacen.controllers;

import com.empresa.gestion_almacen.models.Registro;
import com.empresa.gestion_almacen.models.RegistroRequest;
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
    private Sender sender;

    @GetMapping
    public List<Registro> obtenerRegistros() {
        Object response = sender.sendAndReceive("registro-get-queue", "procesarObtenerRegistros");
        if(response instanceof List<?>) {
            return (List<Registro>) response;
        }
        throw new RuntimeException("Error al obtener registros");
    }

    @PostMapping
    public List<Registro> crearRegistro(@RequestBody Registro registro) {
        Object response = sender.sendAndReceive("registro-post-queue", registro);
        if(response instanceof List<?>) {
            return (List<Registro>) response;
        }
        throw new RuntimeException("Error al crear registro");
    }

    @PutMapping("/{id}")
    public List<Registro> actualizarRegistro(@RequestParam String id, @RequestBody Registro registro) {
        RegistroRequest registroRequest = new RegistroRequest(id, registro);
        Object response = sender.sendAndReceive("registro-put-queue", registroRequest);
        if(response instanceof List<?>) {
            return (List<Registro>) response;
        }
        throw new RuntimeException("Error al actualizar registro");
    }

    @DeleteMapping("/{id}")
    public List<Registro> eliminarRegistro(@PathVariable String id) {
        Object response = sender.sendAndReceive("registro-delete-queue", id);
        if(response instanceof List<?>) {
            return (List<Registro>) response;
        }
        throw new RuntimeException("Error al eliminar registro");
    }
}


