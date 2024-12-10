package com.empresa.gestion_almacen.models;

public class ProductoRequest {
    private String id;
    private Producto producto;
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
