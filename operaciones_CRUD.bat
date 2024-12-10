@echo off
echo Pruebas de operaciones CRUD

:: Crear un almacén
echo Creando un almacén...
curl -X POST -H "Content-Type: application/json" -d "{\"nombre\":\"Almacen 1\", \"ubicacion\":\"Madrid\"}" http://localhost:8080/api/almacenes
echo.

:: Obtener almacenes
echo Obteniendo todos los almacenes...
curl -X GET http://localhost:8080/api/almacenes
echo.

:: Actualizar un almacén
echo Actualizando el almacén con ID 1...
curl -X PUT -H "Content-Type: application/json" -d "{\"nombre\":\"Almacen Actualizado\", \"ubicacion\":\"Barcelona\"}" http://localhost:8080/api/almacenes/1
echo.

:: Eliminar un almacén
echo Eliminando el almacén con ID 1...
curl -X DELETE http://localhost:8080/api/almacenes/1
echo.

:: Repetir las operaciones para productos
echo Creando un producto...
curl -X POST -H "Content-Type: application/json" -d "{\"nombre\":\"Producto 1\", \"precio\":100}" http://localhost:8080/api/productos
echo.

echo Obteniendo todos los productos...
curl -X GET http://localhost:8080/api/productos
echo.

echo Actualizando el producto con ID 1...
curl -X PUT -H "Content-Type: application/json" -d "{\"nombre\":\"Producto Actualizado\", \"precio\":150}" http://localhost:8080/api/productos/1
echo.

echo Eliminando el producto con ID 1...
curl -X DELETE http://localhost:8080/api/productos/1
echo.

:: Repetir las operaciones para registros
echo Creando un registro...
curl -X POST -H "Content-Type: application/json" -d "{\"fecha\":\"2024-12-09\", \"descripcion\":\"Registro de prueba\"}" http://localhost:8080/api/registros
echo.

echo Obteniendo todos los registros...
curl -X GET http://localhost:8080/api/registros
echo.

echo Actualizando el registro con ID 1...
curl -X PUT -H "Content-Type: application/json" -d "{\"fecha\":\"2024-12-10\", \"descripcion\":\"Registro Actualizado\"}" http://localhost:8080/api/registros/1
echo.

echo Eliminando el registro con ID 1...
curl -X DELETE http://localhost:8080/api/registros/1
echo.

echo Pruebas completadas.
pause