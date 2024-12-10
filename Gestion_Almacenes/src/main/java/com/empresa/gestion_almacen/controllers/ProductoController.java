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
    private Sender sender;

    @GetMapping
    public List<Producto> obtenerProductos() {
        Object response = sender.sendAndReceive("producto-get-queue", "procesarObtenerProductos");
        if(response instanceof List<?>) {
            return (List<Producto>) response;
        }
        throw new RuntimeException("Error al obtener productos");
    }

    @PostMapping
    public List<Producto> crearProducto(@RequestBody Producto producto) {
        Object response = sender.sendAndReceive("producto-post-queue", producto);
        if(response instanceof List<?>) {
            return (List<Producto>) response;
        }
        throw new RuntimeException("Error al crear producto");
    }

    @PutMapping("/{id}")
    public List<Producto> actualizarProducto(@PathVariable String id, @RequestBody Producto producto) {
        producto.setId(id);
        Object response = sender.sendAndReceive("producto-put-queue", producto);
        if(response instanceof List<?>) {
            return (List<Producto>) response;
        }
        throw new RuntimeException("Error al actualizar producto");
    }

    @DeleteMapping("/{id}")
    public List<Producto> eliminarProducto(@PathVariable String id) {
        Object response = sender.sendAndReceive("producto-delete-queue", id);
        if(response instanceof List<?>) {
            return (List<Producto>) response;
        }
        throw new RuntimeException("Error al eliminar producto");
    }
}

