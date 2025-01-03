package com.empresa.modelos;

import java.util.ArrayList;
import java.util.List;

public class Producto {

    private String id; // Identificador único del producto
    private String nombre; // Nombre del producto
    private String descripcion; // Descripción del producto
    private int stock; // Cantidad de stock actual
    private int stockMinimo; // Cantidad mínima de stock
    private String almacenId; // ID del almacén asociado
    private List<Registro> registros; // Lista de registros relacionados

    // Constructor vacío
    public Producto() {
        this.registros = new ArrayList<>();
    }

    // Constructor completo
    public Producto(String id, String nombre, String descripcion, int stock, int stockMinimo, String almacenId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.almacenId = almacenId;
        this.registros = new ArrayList<>();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAlmacenId() {
        return almacenId;
    }

    public void setAlmacenId(String almacenId) {
        this.almacenId = almacenId;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    // Método para verificar si el stock está por debajo del mínimo
    public boolean isStockBajo() {
        return stock < stockMinimo;
    }

    // Método para agregar un registro
    public void agregarRegistro(Registro registro) {
        registros.add(registro);
    }

    // Método para mostrar información del producto
    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                ", stockMinimo=" + stockMinimo +
                ", almacenId='" + almacenId + '\'' +
                '}';
    }
}

