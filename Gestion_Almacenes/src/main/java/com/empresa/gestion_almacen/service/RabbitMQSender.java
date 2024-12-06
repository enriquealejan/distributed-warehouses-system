package com.empresa.gestion_almacen.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.empresa.gestion_almacen.config.RabbitMQConfig;

public class RabbitMQSender {
    private static final String QUEUE_NAME = "testQueue";

    public void sendMessage(String message) {
        try (Connection connection = RabbitMQConfig.createConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Mensaje enviado: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}