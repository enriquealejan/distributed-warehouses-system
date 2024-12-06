package com.empresa.gestion_almacen.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "almacenes")
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre_almacen", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ubicacion", length = 255)
    private String direccion;

   /* // Constructor
    public Almacen(int id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }*/

    @OneToMany(mappedBy = "almacen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos = new ArrayList<>();

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}


