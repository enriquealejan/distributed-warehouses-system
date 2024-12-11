package com.empresa.gestion_almacen.service;

import com.empresa.gestion_almacen.models.Almacen;
import com.empresa.gestion_almacen.models.Producto;
import com.empresa.gestion_almacen.models.ProductoRequest;
import com.empresa.gestion_almacen.repositories.ProductoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductoReceiver {

    private static final Logger LOGGER = Logger.getLogger(ProductoReceiver.class.getName());

    @Autowired
    private ProductoRepository productoRepository;

    @RabbitListener(queues = "producto-get-queue")
    public List<Producto> procesarObtenerProductos() {
        System.out.println("Obteniendo productos...");
        return productoRepository.findAll();
    }

    @RabbitListener(queues = "producto-post-queue")
    public List<Producto> procesarCrearProducto(Producto producto) {
        productoRepository.save(producto);

        // Comprobar si se ha creado correctamente el producto
        if(productoRepository.findById(producto.getId()) == null) {
            throw new RuntimeException("No se ha creado correctamente el producto " + producto.getNombre());
        }

        System.out.println("Producto creado: " + producto.getId());
        return productoRepository.findAll();
    }

    @RabbitListener(queues = "producto-put-queue")
    public List<Producto> procesarActualizarProducto(ProductoRequest productoRequest) {
        try {
            LOGGER.info("Recibiendo solicitud de actualización de producto");

            // Log the incoming request for debugging
            LOGGER.info("Producto Request ID: " + productoRequest.getId());
            LOGGER.info("Producto Details: " + productoRequest.getProducto());

            // Validate input
            if (productoRequest.getId() == null || productoRequest.getProducto() == null) {
                throw new IllegalArgumentException("ID o Producto no pueden ser nulos");
            }

            // Find the existing producto
            Optional<Producto> existenteOptional = productoRepository.findById(productoRequest.getId());

            if (existenteOptional.isEmpty()) {
                throw new RuntimeException("Almacén no encontrado con ID: " + productoRequest.getId());
            }

            Producto existente = existenteOptional.get();
            Producto nuevoProducto = productoRequest.getProducto();

            // Update specific fields
            existente.setNombre(nuevoProducto.getNombre());
            existente.setDescripcion(nuevoProducto.getDescripcion());
            existente.setAlmacenId(nuevoProducto.getAlmacenId());

            // Only update registros if it's not empty
            if (nuevoProducto.getRegistros() != null && !nuevoProducto.getRegistros().isEmpty()) {
                existente.setRegistros(nuevoProducto.getRegistros());
            }

            // Save the updated producto
            productoRepository.save(existente);

            LOGGER.info("Producto actualizado exitosamente: " + existente.getId());

            // Return updated list of productos
            return productoRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error en procesarActualizarProducto: " + e.getMessage());
            throw new RuntimeException("Error al procesar actualización de producto", e);
        }
    }

    @RabbitListener(queues = "producto-delete-queue")
    public List<Producto> procesarEliminarProducto(String id) {
        productoRepository.deleteById(id);
        System.out.println("Producto eliminado con ID: " + id);
        return productoRepository.findAll();
    }
}

