package com.empresa.gestion_almacen.controllers;

import com.empresa.gestion_almacen.models.Registro;
import com.empresa.gestion_almacen.models.Producto;
import com.empresa.gestion_almacen.repositories.RegistroRepository;
import com.empresa.gestion_almacen.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros") // Ruta base para los endpoints relacionados con registros
public class RegistroController {
    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // 1. Obtener todos los registros
    @GetMapping
    public List<Registro> getAllRegistros() {
        return registroRepository.findAll();
    }

    // 2. Crear un nuevo registro
    @PostMapping
    public Registro createRegistro(@RequestBody Registro registro) {
        // Validar que el producto asociado exista
        Producto producto = productoRepository.findById(registro.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + registro.getIdProducto()));

        registro.setIdProducto(producto.getId()); // Asociar el producto al registro
        return registroRepository.save(registro);
    }

    // 3. Obtener registros por ID de producto
    @GetMapping("/producto/{productoId}")
    public List<Registro> getRegistrosByProductoId(@PathVariable Long productoId) {
        return registroRepository.findByProductoId(productoId);
    }

    // 4. Eliminar un registro
    @DeleteMapping("/{id}")
    public void deleteRegistro(@PathVariable Long id) {
        Registro registro = registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado con ID: " + id));
        registroRepository.delete(registro);
    }

}
