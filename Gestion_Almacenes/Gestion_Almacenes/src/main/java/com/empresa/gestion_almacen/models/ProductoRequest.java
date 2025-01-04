package com.empresa.gestion_almacen.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductoRequest {
    private String id;
    private Producto producto;

    public ProductoRequest(){
    }
    public ProductoRequest(String id, Producto producto){
        this.id = id;
        this.producto = producto;
    }
    public String getId(){
        return this.id;
    }

    public String setId(String id){
        return this.id = id;
    }

    public Producto getProducto(){
        return this.producto;
    }

    public Producto setProducto(Producto producto){
        return this.producto = producto;
    }
}
