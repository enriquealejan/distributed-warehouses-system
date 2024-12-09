package com.empresa.gestion_almacen.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;


public class RabbitMQConfigRegistro {
    // Colas para operaciones en Registro
    public static final String QUEUE_REGISTRO_GET = "registro-get-queue";

    public static final String QUEUE_REGISTRO_POST = "registro-post-queue";
    public static final String QUEUE_REGISTRO_PUT = "registro-put-queue";
    public static final String QUEUE_REGISTRO_DELETE = "registro-delete-queue";

    public static final String EXCHANGE_GENERAL = "registro_exchange";

    // Routing keys para Registro
    public static final String ROUTING_KEY_REGISTRO_GET = "registro.get";
    public static final String ROUTING_KEY_REGISTRO_POST = "registro.post";
    public static final String ROUTING_KEY_REGISTRO_PUT = "registro.put";
    public static final String ROUTING_KEY_REGISTRO_DELETE = "registro.delete";

    @Bean
    public Queue registroGetQueue() {
        return new Queue(QUEUE_REGISTRO_GET, true);
    }
    @Bean
    public Queue registroPostQueue() {
        return new Queue(QUEUE_REGISTRO_POST, true);
    }

    @Bean
    public Queue registroPutQueue() {
        return new Queue(QUEUE_REGISTRO_PUT, true);
    }

    @Bean
    public Queue registroDeleteQueue() {
        return new Queue(QUEUE_REGISTRO_DELETE, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_GENERAL);
    }


    // Bindings para Registro
    @Bean
    public Binding registroGetBinding(Queue registroGetQueue, DirectExchange exchange) {
        return BindingBuilder.bind(registroGetQueue).to(exchange).with(ROUTING_KEY_REGISTRO_GET);
    }

    @Bean
    public Binding registroPostBinding(Queue registroPostQueue, DirectExchange exchange) {
        return BindingBuilder.bind(registroPostQueue).to(exchange).with(ROUTING_KEY_REGISTRO_POST);
    }

    @Bean
    public Binding registroPutBinding(Queue registroPutQueue, DirectExchange exchange) {
        return BindingBuilder.bind(registroPutQueue).to(exchange).with(ROUTING_KEY_REGISTRO_PUT);
    }

    @Bean
    public Binding registroDeleteBinding(Queue registroDeleteQueue, DirectExchange exchange) {
        return BindingBuilder.bind(registroDeleteQueue).to(exchange).with(ROUTING_KEY_REGISTRO_DELETE);
    }
}
