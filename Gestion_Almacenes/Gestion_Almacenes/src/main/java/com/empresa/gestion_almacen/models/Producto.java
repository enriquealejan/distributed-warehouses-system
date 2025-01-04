package com.empresa.gestion_almacen.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "productos") // Nombre de la colección en MongoDB
public class Producto {

    @Id
    private String id; // MongoDB usa identificadores tipo String

    @Field("nombre_producto") // Nombre del campo en la colección
    private String nombre;

    @Field("descripcion")
    private String descripcion;

    @Field("stock")
    private int stock;

    @Field("stock_minimo")
    private int stockMinimo;

    // Relación con Almacen: Referencia por ID
    @Field("almacen_id")
    private String almacenId; // Guardamos solo el ID del almacén

    // Relación con Registro: Lista de registros embebidos
    @Field("registros")
    private List<Registro> registros = new ArrayList<>();

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
}
