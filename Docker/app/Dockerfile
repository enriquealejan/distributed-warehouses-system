FROM eclipse-temurin:17-jdk-jammy

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado por el proyecto
ADD Gestion_Almacenes-1.0-SNAPSHOT.jar app.jar

# Exponer el puerto que usa la app
EXPOSE 8080

# Ejecutar la app
ENTRYPOINT [ "java", "-jar", "app.jar" ]
