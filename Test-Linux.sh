#!/bin/bash

# Realizando pruebas CRUD con formato JSON anidado...
# Pruebas almacén
echo "POST Crear Almacen"
curl -X POST http://localhost:8080/api/almacenes -H "Content-Type: application/json" -d '{"id":"123","nombre":"Nuevo Almacen","ubicacion":"Barcelona","productosIds":["prod3","prod4"]}'
echo

echo "GET Obtener Almacenes"
curl -X GET http://localhost:8080/api/almacenes
echo

echo "PUT Actualizar Almacen"
curl -X PUT http://localhost:8080/api/almacenes/123 -H "Content-Type: application/json" -d '{"nombre":"Almacen Viejo","ubicacion":"Madrid","productosIds":["prod2","prod5"]}'
echo

echo "DELETE Eliminar Almacen"
curl -X DELETE http://localhost:8080/api/almacenes/123
echo

# Pruebas producto
echo "POST Producto"
curl -X POST http://localhost:8080/api/productos -H "Content-Type: application/json" -d '{"id": "456","nombre": "Producto 1", "descripcion": "Descripcion 1", "almacenId": "123"}'
echo

echo "GET Todos los Productos"
curl -X GET http://localhost:8080/api/productos
echo

echo "PUT Actualizar Producto"
curl -X PUT http://localhost:8080/api/productos/456 -H "Content-Type: application/json" -d '{"nombre": "Producto Anidado", "descripcion": "Nueva Descripcion", "almacenId": "123"}'
echo

echo "DELETE Producto"
curl -X DELETE http://localhost:8080/api/productos/456
echo

# Pruebas registro
echo "POST Registro"
curl -X POST http://localhost:8080/api/registros -H "Content-Type: application/json" -d '{"id": "789", "productoId": "456", "cantidad": 10, "fechaMovimiento": "2024-12-10T00:00:00"}'
echo

echo "GET Todos los Registros"
curl -X GET http://localhost:8080/api/registros
echo

echo "PUT Actualizar Registro"
curl -X PUT http://localhost:8080/api/registros/789 -H "Content-Type: application/json" -d '{"productoId": "456", "cantidad": 20, "fechaMovimiento": "2024-12-11T00:00:00"}'
echo

echo "DELETE Registro"
curl -X DELETE http://localhost:8080/api/registros/789
echo

