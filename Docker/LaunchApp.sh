#!/bin/bash

# Asegurarnos de descargar la imagen de mvn para compilar el proyecto
docker pull maven:latest # La versión se puede cambiar

cd .. # Me voy a la carpeta raíz del proyecto

# Lanzar el contenedor de maven 
docker run -it --rm --name project_compiler -v ./Gestion_Almacenes/:/app -w /app maven:latest mvn clean package # -v <source> lo cambias en función de donde se tenga el proyecto

cd Docker # Vuelvo a la carpeta de Docker

# El .jar generado lo copio en el directorio de app (evitar posibles problemas)
cp ../Gestion_Almacenes/target/Gestion_Almacenes-1.0-SNAPSHOT.jar app

# Una vez compilado el proyecto, creo la red para que mis contenedores interactuen
docker network create app-network

# Con la red creada, lanzo mi app
docker-compose up --build


