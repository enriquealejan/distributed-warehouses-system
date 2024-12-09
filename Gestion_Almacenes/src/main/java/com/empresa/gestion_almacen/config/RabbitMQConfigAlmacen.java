package com.empresa.gestion_almacen.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfigAlmacen {

    public static final String QUEUE_ALMACEN_GET = "almacen-get-queue";
    // Colas para operaciones en Almacén
    public static final String QUEUE_ALMACEN_POST = "almacen-post-queue";
    public static final String QUEUE_ALMACEN_PUT = "almacen-put-queue";
    public static final String QUEUE_ALMACEN_DELETE = "almacen-delete-queue";

    public static final String EXCHANGE_GENERAL = "almacen_exchange";

    // Routing keys
    public static final String ROUTING_KEY_ALMACEN_GET = "almacen.get";
    public static final String ROUTING_KEY_ALMACEN_POST = "almacen.post";
    public static final String ROUTING_KEY_ALMACEN_PUT = "almacen.put";
    public static final String ROUTING_KEY_ALMACEN_DELETE = "almacen.delete";

    public static final String ROUTING_KEY_ALMACEN = "almacen_routing_key";

    @Bean
    public Queue almacenGetQueue() {
        return new Queue(QUEUE_ALMACEN_GET, true);
    }

    @Bean
    public Queue almacenPostQueue() {
        return new Queue(QUEUE_ALMACEN_POST, true);
    }

    @Bean
    public Queue almacenPutQueue() {
        return new Queue(QUEUE_ALMACEN_PUT, true);
    }

    @Bean
    public Queue almacenDeleteQueue() {
        return new Queue(QUEUE_ALMACEN_DELETE, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_GENERAL);
    }

    // Bindings para Almacén
    @Bean
    public Binding almacenGetBinding(Queue almacenGetQueue, DirectExchange exchange) {
        return BindingBuilder.bind(almacenGetQueue).to(exchange).with(ROUTING_KEY_ALMACEN_GET);
    }
    @Bean
    public Binding almacenPostBinding(Queue almacenPostQueue, DirectExchange exchange) {
        return BindingBuilder.bind(almacenPostQueue).to(exchange).with(ROUTING_KEY_ALMACEN_POST);
    }

    @Bean
    public Binding almacenPutBinding(Queue almacenPutQueue, DirectExchange exchange) {
        return BindingBuilder.bind(almacenPutQueue).to(exchange).with(ROUTING_KEY_ALMACEN_PUT);
    }

    @Bean
    public Binding almacenDeleteBinding(Queue almacenDeleteQueue, DirectExchange exchange) {
        return BindingBuilder.bind(almacenDeleteQueue).to(exchange).with(ROUTING_KEY_ALMACEN_DELETE);
    }
}
