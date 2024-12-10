package com.empresa.gestion_almacen.service;

import com.empresa.gestion_almacen.models.Almacen;
import com.empresa.gestion_almacen.models.AlmacenRequest;
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

    @RabbitListener(queues = "almacen-put-queue")
    public List<Almacen> procesarActualizarAlmacen(AlmacenRequest almacen) {
        String id_almacen = almacen.getId();
        Almacen almac= almacen.getAlmacen();

        Almacen existente = almacenRepository.findById(id_almacen)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado con ID: " + id_almacen));
        existente.setNombre(almac.getNombre());
        existente.setUbicacion(almac.getUbicacion());
        almacenRepository.save(existente);
        System.out.println("Almacén actualizado: " + almacen.getId());
        return almacenRepository.findAll();
    }

    @RabbitListener(queues = "almacen-delete-queue")
    public List<Almacen> procesarEliminarAlmacen(String id) {
        almacenRepository.deleteById(id);
        System.out.println("Almacén eliminado con ID: " + id);
        return almacenRepository.findAll();
    }
}
