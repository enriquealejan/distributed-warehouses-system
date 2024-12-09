package com.empresa.gestion_almacen.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfigProducto {
    // Colas para operaciones en Producto
    public static final String QUEUE_PRODUCTO_GET = "producto-get-queue";
    public static final String QUEUE_PRODUCTO_POST = "producto-post-queue";
    public static final String QUEUE_PRODUCTO_PUT = "producto-put-queue";
    public static final String QUEUE_PRODUCTO_DELETE = "producto-delete-queue";

    public static final String EXCHANGE_PRODUCTO = "producto_exchange";

    // Routing keys
    public static final String ROUTING_KEY_PRODUCTO_GET = "producto.get";
    public static final String ROUTING_KEY_PRODUCTO_POST = "producto.post";
    public static final String ROUTING_KEY_PRODUCTO_PUT = "producto.put";
    public static final String ROUTING_KEY_PRODUCTO_DELETE = "producto.delete";

    @Bean
    public Queue productoGetQueue() {
        return new Queue(QUEUE_PRODUCTO_GET, true);
    }

    @Bean
    public Queue productoPostQueue() {
        return new Queue(QUEUE_PRODUCTO_POST, true);
    }

    @Bean
    public Queue productoPutQueue() {
        return new Queue(QUEUE_PRODUCTO_PUT, true);
    }

    @Bean
    public Queue productoDeleteQueue() {
        return new Queue(QUEUE_PRODUCTO_DELETE, true);
    }

    @Bean(name = "exchangeProducto")
    public DirectExchange exchangeProducto() {
        return new DirectExchange(EXCHANGE_PRODUCTO);
    }

    // Bindings para Producto
    @Bean
    public Binding productoGetBinding(Queue productoGetQueue, @Qualifier("exchangeProducto") DirectExchange exchange) {
        return BindingBuilder.bind(productoGetQueue).to(exchange).with(ROUTING_KEY_PRODUCTO_GET);
    }

    @Bean
    public Binding productoPostBinding(Queue productoPostQueue, @Qualifier("exchangeProducto") DirectExchange exchange) {
        return BindingBuilder.bind(productoPostQueue).to(exchange).with(ROUTING_KEY_PRODUCTO_POST);
    }

    @Bean
    public Binding productoPutBinding(Queue productoPutQueue, @Qualifier("exchangeProducto") DirectExchange exchange) {
        return BindingBuilder.bind(productoPutQueue).to(exchange).with(ROUTING_KEY_PRODUCTO_PUT);
    }

    @Bean
    public Binding productoDeleteBinding(Queue productoDeleteQueue, @Qualifier("exchangeProducto") DirectExchange exchange) {
        return BindingBuilder.bind(productoDeleteQueue).to(exchange).with(ROUTING_KEY_PRODUCTO_DELETE);
    }
}
