package com.empresa.gestion_almacen.service;

import com.empresa.gestion_almacen.models.Almacen;
import com.empresa.gestion_almacen.repositories.AlmacenRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlmacenReceiver {

    @Autowired
    private AlmacenRepository almacenRepository;

    @RabbitListener(queues = "almacen-get-queue")
    public void procesarObtenerAlmacenes() {
        System.out.println("Obteniendo almacenes...");
        almacenRepository.findAll().forEach(almacen -> System.out.println(almacen.getNombre()));
    }

    @RabbitListener(queues = "almacen-post-queue")
    public void procesarCrearAlmacen(Almacen almacen) {
        almacenRepository.save(almacen);
        System.out.println("Almacén creado: " + almacen.getId());
    }

    @RabbitListener(queues = "almacen-put-queue")
    public void procesarActualizarAlmacen(Almacen almacen) {
        Almacen existente = almacenRepository.findById(almacen.getId())
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado con ID: " + almacen.getId()));
        existente.setNombre(almacen.getNombre());
        existente.setUbicacion(almacen.getUbicacion());
        almacenRepository.save(existente);
        System.out.println("Almacén actualizado: " + almacen.getId());
    }

    @RabbitListener(queues = "almacen-delete-queue")
    public void procesarEliminarAlmacen(String id) {
        almacenRepository.deleteById(id);
        System.out.println("Almacén eliminado con ID: " + id);
    }
}
