package com.empresa.gestion_almacen.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos") // Nombre personalizado para la tabla
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Clave primaria autogenerada
    private Long id;

    @Column(name = "nombre_producto", nullable = false, length = 100) // Nombre del producto
    private String nombre;

    @Column(name = "descripcion", length = 255) // Descripción opcional
    private String descripcion;

    @Column(name = "stock", nullable = false) // Cantidad en inventario
    private int stock;

    @Column(name = "stock_minimo", nullable = false) // Nivel mínimo de stock permitido
    private int stockMinimo;

    // Relación con Almacen (Muchos productos pertenecen a un único almacén)
    @ManyToOne
    @JoinColumn(name = "almacen_id", nullable = false) // Llave foránea hacia almacén
    private Almacen almacen;

    // Relación con Registro (Un producto puede tener múltiples registros de movimientos)
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registro> registros = new ArrayList<>();

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }
}

