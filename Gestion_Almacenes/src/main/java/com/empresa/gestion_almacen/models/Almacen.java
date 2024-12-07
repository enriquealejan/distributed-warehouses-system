package com.empresa.gestion_almacen.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "almacenes") // Nombre de la colección en MongoDB
public class Almacen {

    @Id
    private String id; // MongoDB usa identificadores tipo String

    @Field("nombre")
    private String nombre;

    @Field("ubicacion")
    private String ubicacion;

    // Relación con Producto: Referencias por ID
    @Field("productos_ids")
    private List<String> productosIds = new ArrayList<>(); // IDs de productos relacionados

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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<String> getProductosIds() {
        return productosIds;
    }

    public void setProductosIds(List<String> productosIds) {
        this.productosIds = productosIds;
    }
}
