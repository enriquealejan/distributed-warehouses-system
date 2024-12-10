package com.empresa.gestion_almacen.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductoRequest {
    @JsonProperty ("id") private String id;
    @JsonProperty ("producto") private Producto producto;

    public ProductoRequest(){
    }
    public ProductoRequest(String id, Producto producto){
        this.id = id;
        this.producto = producto;
    }
    public String getId(){
        return this.id;
    }

    public Producto getProducto(){
        return this.producto;
    }
}
