package com.empresa.gestion_almacen.service;

import com.empresa.gestion_almacen.models.Producto;
import com.empresa.gestion_almacen.repositories.ProductoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoReceiver {

    @Autowired
    private ProductoRepository productoRepository;

    @RabbitListener(queues = "producto-get-queue")
    public void procesarObtenerProductos() {
        System.out.println("Obteniendo productos...");
        productoRepository.findAll().forEach(producto -> System.out.println(producto.getNombre()));
    }

    @RabbitListener(queues = "producto-post-queue")
    public void procesarCrearProducto(Producto producto) {
        productoRepository.save(producto);
        System.out.println("Producto creado: " + producto.getId());
    }

    @RabbitListener(queues = "producto-put-queue")
    public void procesarActualizarProducto(Producto producto) {
        Producto existente = productoRepository.findById(producto.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + producto.getId()));
        existente.setNombre(producto.getNombre());
        existente.setStock(producto.getStock());
        productoRepository.save(existente);
        System.out.println("Producto actualizado: " + producto.getId());
    }

    @RabbitListener(queues = "producto-delete-queue")
    public void procesarEliminarProducto(String id) {
        productoRepository.deleteById(id);
        System.out.println("Producto eliminado con ID: " + id);
    }
}
