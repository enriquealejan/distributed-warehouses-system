package com.empresa.gestion_almacen.service;

import com.empresa.gestion_almacen.models.Registro;
import com.empresa.gestion_almacen.repositories.RegistroRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroReceiver {

    @Autowired
    private RegistroRepository registroRepository;

    @RabbitListener(queues = "registro-get-queue")
    public void procesarObtenerRegistros() {
        System.out.println("Obteniendo registros...");
        registroRepository.findAll().forEach(registro -> System.out.println(registro.getTipoMovimiento()));
    }

    @RabbitListener(queues = "registro-post-queue")
    public void procesarCrearRegistro(Registro registro) {
        registroRepository.save(registro);
        System.out.println("Registro creado: " + registro.getId());
    }

    @RabbitListener(queues = "registro-put-queue")
    public void procesarActualizarRegistro(Registro registro) {
        Registro existente = registroRepository.findById(registro.getId())
                .orElseThrow(() -> new RuntimeException("Registro no encontrado con ID: " + registro.getId()));
        existente.setTipoMovimiento(registro.getTipoMovimiento());
        existente.setFechaMovimiento(registro.getFechaMovimiento());
        registroRepository.save(existente);
        System.out.println("Registro actualizado: " + registro.getId());
    }

    @RabbitListener(queues = "registro-delete-queue")
    public void procesarEliminarRegistro(String id) {
        registroRepository.deleteById(id);
        System.out.println("Registro eliminado con ID: " + id);
    }
}

