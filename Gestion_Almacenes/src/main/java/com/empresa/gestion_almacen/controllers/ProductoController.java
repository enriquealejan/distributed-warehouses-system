package com.empresa.gestion_almacen.controllers;

import com.empresa.gestion_almacen.models.Producto;
import com.empresa.gestion_almacen.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable String id, @RequestBody Producto producto) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setStock(producto.getStock());
        existente.setStockMinimo(producto.getStockMinimo());
        return productoRepository.save(existente);
    }
}
