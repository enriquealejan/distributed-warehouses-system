# Asegurarnos de descargar la imagen de mvn para compilar el proyecto
docker pull maven:latest # Cambia la versión si es necesario

cd..
# Lanzar el contenedor de Maven
docker run -it --rm --name project_compiler -v .\Gestion_Almacenes\:/app -w /app maven:latest mvn clean package # Ajusta la ruta ../Gestion_Almacenes/ según la ubicación del proyecto en Windows
cd Docker
# Copiar el archivo .jar generado al directorio de app (evitar posibles problemas)
Copy-Item "..\Gestion_Almacenes\target\Gestion_Almacenes-1.0-SNAPSHOT.jar" .\app\

# Crear la red para que los contenedores interactúen
docker network create app-network

# Con la red creada, lanzar la app usando docker-compose
docker-compose up --build

