package com.empresa.gestion_almacen.service;

import com.empresa.gestion_almacen.models.Registro;
import com.empresa.gestion_almacen.repositories.RegistroRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroReceiver {

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
    public List<Registro> procesarActualizarRegistro(Registro registro) {
        Registro existente = registroRepository.findById(registro.getId())
                .orElseThrow(() -> new RuntimeException("Registro no encontrado con ID: " + registro.getId()));
        existente.setFechaMovimiento(registro.getFechaMovimiento());
        existente.setTipoMovimiento(registro.getTipoMovimiento());
        registroRepository.save(existente);
        System.out.println("Registro actualizado: " + registro.getId());
        return registroRepository.findAll();
    }

    @RabbitListener(queues = "registro-delete-queue")
    public List<Registro> procesarEliminarRegistro(String id) {
        registroRepository.deleteById(id);
        System.out.println("Registro eliminado con ID: " + id);
        return registroRepository.findAll();
    }
}

