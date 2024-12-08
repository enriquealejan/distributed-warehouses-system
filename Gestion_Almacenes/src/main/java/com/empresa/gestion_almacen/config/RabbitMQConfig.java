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

/*
package com.empresa.gestion_almacen.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Valores cargados desde application.properties
    @Value("${rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.username}")
    private String username;

    @Value("${rabbitmq.password}")
    private String password;

    // Nombres de la cola, el intercambio y la clave de enrutamiento
    public static final String QUEUE_NAME = "mi_cola";
    public static final String EXCHANGE_NAME = "mi_intercambio";
    public static final String ROUTING_KEY = "mi.routing.key";

    // Configurar la conexión con RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory(host);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }

    // Declarar una cola
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true); // Cola duradera
    }

    // Declarar un intercambio tipo Topic
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // Enlazar la cola con el intercambio usando la clave de enrutamiento
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    // Configurar el RabbitTemplate para enviar mensajes
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    // Listener Container opcional (solo necesario si no usas @RabbitListener)
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(queue());
        container.setMessageListener(message ->
            System.out.println("Mensaje recibido: " + new String(message.getBody())));
        return container;
    }
}

 */
