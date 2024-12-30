# Usar la imagen oficial de RabbitMQ
FROM rabbitmq:3.12-management

# Exponer los puertos necesarios
EXPOSE 5672 15672

# Definir entorno opcionalmente
ENV RABBITMQ_DEFAULT_USER=guest
ENV RABBITMQ_DEFAULT_PASS=guest

# El contenedor ejecutará RabbitMQ
CMD ["rabbitmq-server"]
