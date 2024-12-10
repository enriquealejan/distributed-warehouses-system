package com.empresa.gestion_almacen.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Object sendAndReceive(String queueName, Object message) {
        return rabbitTemplate.convertSendAndReceive(queueName, message);
    }
}
