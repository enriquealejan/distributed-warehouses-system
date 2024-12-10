package com.empresa.gestion_almacen.service;

import com.empresa.gestion_almacen.models.Producto;
import com.empresa.gestion_almacen.models.Registro;
import com.empresa.gestion_almacen.models.RegistroRequest;
import com.empresa.gestion_almacen.repositories.RegistroRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class RegistroReceiver {

    private static final Logger LOGGER = Logger.getLogger(RegistroReceiver.class.getName());

    @Autowired
    private RegistroRepository registroRepository;

    @RabbitListener(queues = "registro-get-queue")
    public List<Registro> procesarObtenerRegistros() {
        System.out.println("Obteniendo registros...");
        return registroRepository.findAll();
    }

    @RabbitListener(queues = "registro-post-queue")
    public List<Registro> procesarCrearRegistro(Registro registro) {
        registroRepository.save(registro);
        System.out.println("Registro creado: " + registro.getId());
        return registroRepository.findAll();
    }

    @RabbitListener(queues = "registro-put-queue")
    public List<Registro> procesarActualizarRegistro(RegistroRequest registroRequest) {
        try{
            LOGGER.info("Recibiendo solicitud de actualización de producto");

            // Log the incoming request for debugging
            LOGGER.info("Registro Request ID: " + registroRequest.getId());
            LOGGER.info("Registro Details: " + registroRequest.getRegistro());

            // Validate input
            if (registroRequest.getId() == null || registroRequest.getRegistro() == null) {
                throw new IllegalArgumentException("ID o Producto no pueden ser nulos");
            }

            // Find the existing producto
            Optional<Registro> existenteOptional = registroRepository.findById(registroRequest.getId());

            if (existenteOptional.isEmpty()) {
                throw new RuntimeException("Almacén no encontrado con ID: " + registroRequest.getId());
            }

            Registro existente = existenteOptional.get();
            Registro nuevoRegistro = registroRequest.getRegistro();

            // Update specific fields
            existente.setIdProducto(nuevoRegistro.getIdProducto());
            existente.setCantidad(nuevoRegistro.getCantidad());
            existente.setFechaMovimiento(nuevoRegistro.getFechaMovimiento());

            // Save the updated producto
            registroRepository.save(existente);

            LOGGER.info("Producto actualizado exitosamente: " + existente.getId());

            // Return updated list of productos
            return registroRepository.findAll();

        }catch(Exception e){
            LOGGER.severe("Error en procesarActualizarRegistro: " + e.getMessage());
            throw new RuntimeException("Error al procesar actualización de registro", e);
        }
    }

    @RabbitListener(queues = "registro-delete-queue")
    public List<Registro> procesarEliminarRegistro(String id) {
        registroRepository.deleteById(id);
        System.out.println("Registro eliminado con ID: " + id);
        return registroRepository.findAll();
    }
}

