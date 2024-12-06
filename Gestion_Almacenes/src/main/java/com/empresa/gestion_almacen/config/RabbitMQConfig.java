package com.empresa.gestion_almacen.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConfig {
    private static final String HOST = "localhost"; // Aquí se puede cambiar por la IP del servidor de RabbitMQ
    private static final String USERNAME = "guest"; // Usuario predeterminado de RabbitMQ
    private static final String PASSWORD = "guest"; // Contraseña predeterminada de RabbitMQ

    public static Connection createConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);

        // Configuración adicional si es necesaria
        // factory.setPort(5672); // Puerto predeterminado de RabbitMQ

        return factory.newConnection();
    }
}

