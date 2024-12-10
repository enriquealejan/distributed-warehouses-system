package com.empresa.gestion_almacen.models;

public class AlmacenRequest{
    private String id;
    private Almacen almacen;
    public AlmacenRequest(String id, Almacen almacen){
        this.id = id;
        this.almacen = almacen;
    }
    public String getId(){
        return this.id;
    }

    public Almacen getAlmacen(){
        return this.almacen;
    }
}
