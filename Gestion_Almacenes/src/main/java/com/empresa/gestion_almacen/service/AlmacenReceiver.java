package com.empresa.gestion_almacen.service;

import com.empresa.gestion_almacen.models.Almacen;
import com.empresa.gestion_almacen.models.AlmacenRequest;
import com.empresa.gestion_almacen.repositories.AlmacenRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AlmacenReceiver {

    private static final Logger LOGGER = Logger.getLogger(AlmacenReceiver.class.getName());

    @Autowired
    private AlmacenRepository almacenRepository;

    @RabbitListener(queues = "almacen-get-queue")
    public List<Almacen> procesarObtenerAlmacenes() {
        System.out.println("Obteniendo almacenes...");
        return almacenRepository.findAll();
    }

    @RabbitListener(queues = "almacen-post-queue")
    public List<Almacen> procesarCrearAlmacen(Almacen almacen) {
        almacenRepository.save(almacen);
        System.out.println("Almacén creado: " + almacen.getId());
        return almacenRepository.findAll();
    }

    @RabbitListener(queues = "almacen-put-queue")
    public List<Almacen> procesarActualizarAlmacen(AlmacenRequest almacenRequest) {
        try {
            LOGGER.info("Recibiendo solicitud de actualización de almacén");

            // Log the incoming request for debugging
            LOGGER.info("Almacen Request ID: " + almacenRequest.getId());
            LOGGER.info("Almacen Details: " + almacenRequest.getAlmacen());

            // Validate input
            if (almacenRequest.getId() == null || almacenRequest.getAlmacen() == null) {
                throw new IllegalArgumentException("ID o Almacen no pueden ser nulos");
            }

            // Find the existing almacen
            Optional<Almacen> existenteOptional = almacenRepository.findById(almacenRequest.getId());

            if (existenteOptional.isEmpty()) {
                throw new RuntimeException("Almacén no encontrado con ID: " + almacenRequest.getId());
            }

            Almacen existente = existenteOptional.get();
            Almacen nuevoAlmacen = almacenRequest.getAlmacen();

            // Update specific fields
            existente.setNombre(nuevoAlmacen.getNombre());
            existente.setUbicacion(nuevoAlmacen.getUbicacion());

            // Only update productosIds if it's not empty
            if (nuevoAlmacen.getProductosIds() != null && !nuevoAlmacen.getProductosIds().isEmpty()) {
                existente.setProductosIds(nuevoAlmacen.getProductosIds());
            }

            // Save the updated almacen
            almacenRepository.save(existente);

            LOGGER.info("Almacén actualizado exitosamente: " + existente.getId());

            // Return updated list of almacenes
            return almacenRepository.findAll();

        } catch (Exception e) {
            LOGGER.severe("Error en procesarActualizarAlmacen: " + e.getMessage());
            throw new RuntimeException("Error al procesar actualización de almacén", e);
        }
    }

    @RabbitListener(queues = "almacen-delete-queue")
    public List<Almacen> procesarEliminarAlmacen(String id) {
        almacenRepository.deleteById(id);
        System.out.println("Almacén eliminado con ID: " + id);
        return almacenRepository.findAll();
    }
}
