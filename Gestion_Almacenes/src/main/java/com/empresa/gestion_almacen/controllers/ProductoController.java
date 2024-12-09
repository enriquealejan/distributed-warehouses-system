package com.empresa.gestion_almacen.controllers;

import com.empresa.gestion_almacen.models.Producto;
import com.empresa.gestion_almacen.repositories.ProductoRepository;
import com.empresa.gestion_almacen.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private Sender sender; // Servicio que envía mensajes a las colas

    @GetMapping
    public String enviarObtenerProductos() {
        sender.sendMessage("producto-get-queue", "Obtener productos");
        return "Solicitud de obtención de productos enviada a la cola.";
    }

    @PostMapping
    public String enviarCrearProducto(@RequestBody Producto producto) {
        sender.sendMessage("producto-post-queue", producto);
        return "Solicitud de creación de producto enviada a la cola.";
    }

    @PutMapping("/{id}")
    public String enviarActualizarProducto(@PathVariable String id, @RequestBody Producto producto) {
        producto.setId(id);
        sender.sendMessage("producto-put-queue", producto);
        return "Solicitud de actualización de producto enviada a la cola.";
    }

    @DeleteMapping("/{id}")
    public String enviarEliminarProducto(@PathVariable String id) {
        sender.sendMessage("producto-delete-queue", id);
        return "Solicitud de eliminación de producto enviada a la cola.";
    }
}
