package com.empresa.gestion_almacen;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQChecker implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            rabbitTemplate.convertAndSend("example-queue", "Mensaje de prueba");
            System.out.println("RabbitMQ está funcionando correctamente.");
        } catch (Exception e) {
            System.err.println("Error al conectarse a RabbitMQ: " + e.getMessage());
        }
    }
}