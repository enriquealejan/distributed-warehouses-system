#!/bin/bash

echo "Pruebas CRUD para AlmacenRequest"

# POST
echo "POST Almacen"
curl -X POST http://localhost:8080/api/almacenes -H "Content-Type: application/json" -d '{"nombre": "Almacen 1", "ubicacion": "Madrid", "productosIds": ["prod1", "prod2"]}'
echo

# GET
echo "GET Todos los Almacenes"
curl -X GET http://localhost:8080/api/almacenes
echo

# PUT Almacen
echo "PUT Actualizar Almacen"
curl -X PUT http://localhost:8080/api/almacenes/123 -H "Content-Type: application/json" -d '{"id": "123", "almacen": {"nombre": "Almacen Anidado", "ubicacion": "Barcelona", "productosIds": []}}'
echo

# DELETE
echo "DELETE Almacen"
curl -X DELETE http://localhost:8080/api/almacenes/123
echo


echo "Pruebas CRUD para ProductoRequest"

# POST
echo "POST Producto"
curl -X POST http://localhost:8080/api/productos -H "Content-Type: application/json" -d '{"nombre": "Producto 1", "descripcion": "Descripcion 1", "almacenId": "123"}'
echo

# GET
echo "GET Todos los Productos"
curl -X GET http://localhost:8080/api/productos
echo

# PUT Producto
echo "PUT Actualizar Producto"
curl -X PUT http://localhost:8080/api/productos/456 -H "Content-Type: application/json" -d '{"id": "456", "producto": {"nombre": "Producto Anidado", "descripcion": "Nueva Descripcion", "almacenId": "123"}}'
echo

# DELETE
echo "DELETE Producto"
curl -X DELETE http://localhost:8080/api/productos/456
echo


echo "Pruebas CRUD para RegistroRequest"

# POST
echo "POST Registro"
curl -X POST http://localhost:8080/api/registros -H "Content-Type: application/json" -d '{"productoId": "456", "cantidad": 10, "fecha": "2024-12-10"}'
echo

# GET
echo "GET Todos los Registros"
curl -X GET http://localhost:8080/api/registros
echo

# PUT Registro
echo "PUT Actualizar Registro"
curl -X PUT http://localhost:8080/api/registros/789 -H "Content-Type: application/json" -d '{"id": "789", "registro": {"productoId": "456", "cantidad": 20, "fecha": "2024-12-11"}}'
echo

# DELETE
echo "DELETE Registro"
curl -X DELETE http://localhost:8080/api/registros/789
echo
