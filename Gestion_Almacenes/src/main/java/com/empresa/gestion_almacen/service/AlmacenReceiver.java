package com.empresa.gestion_almacen.service;

import com.empresa.gestion_almacen.models.Almacen;
import com.empresa.gestion_almacen.repositories.AlmacenRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlmacenReceiver {

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

    /*public void procesarCrearAlmacen(Almacen almacen) {
        almacenRepository.save(almacen);
        System.out.println("Almacén creado: " + almacen.getId());
    }*/

    @RabbitListener(queues = "almacen-put-queue")
    public List<Almacen> procesarActualizarAlmacen(Almacen almacen) {
        Almacen existente = almacenRepository.findById(almacen.getId())
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado con ID: " + almacen.getId()));
        existente.setNombre(almacen.getNombre());
        existente.setUbicacion(almacen.getUbicacion());
        almacenRepository.save(existente);
        System.out.println("Almacén actualizado: " + almacen.getId());
        return almacenRepository.findAll();
    }

    /*
    public void procesarActualizarAlmacen(Almacen almacen) {
        Almacen existente = almacenRepository.findById(almacen.getId())
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado con ID: " + almacen.getId()));
        existente.setNombre(almacen.getNombre());
        existente.setUbicacion(almacen.getUbicacion());
        almacenRepository.save(existente);
        System.out.println("Almacén actualizado: " + almacen.getId());
    }*/

    @RabbitListener(queues = "almacen-delete-queue")
    public List<Almacen> procesarEliminarAlmacen(String id) {
        almacenRepository.deleteById(id);
        System.out.println("Almacén eliminado con ID: " + id);
        return almacenRepository.findAll();
    }
    /*
    public void procesarEliminarAlmacen(String id) {
        almacenRepository.deleteById(id);
        System.out.println("Almacén eliminado con ID: " + id);
    }
    */
}
