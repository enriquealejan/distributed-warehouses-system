package com.empresa.gestion_almacen.service;

import com.rabbitmq.client.*;
import com.empresa.gestion_almacen.config.RabbitMQConfig;

public class RabbitMQReceiver {
    private static final String QUEUE_NAME = "testQueue";

    public void receiveMessage() {
        try (Connection connection = RabbitMQConfig.createConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Esperando mensajes...");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("Mensaje recibido: " + message);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
