@echo off

echo Realizando pruebas CRUD con formato JSON anidado...

REM GET Almacen
echo GET Obtener Almacenes
curl -X GET http://localhost:8080/api/almacenes
echo.

curl -X PUT http://localhost:8080/api/almacenes/3D -H "Content-Type: application/json" -d "{\"nombre\":\"Nuevo Almacen\",\"ubicacion\":\"Barcelona\",\"productosIds\":[\"prod3\",\"prod4\"]}"
echo Pruebas CRUD para ProductoRequest

REM GET
echo GET Todos los Productos
curl -X GET http://localhost:8080/api/productos
echo.

echo Pruebas CRUD para RegistroRequest

REM GET
echo GET Todos los Registros
curl -X GET http://localhost:8080/api/registros
echo.

pause