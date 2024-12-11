package com.empresa.gestion_almacen.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AlmacenRequest{
    private String id;
    private Almacen almacen;
    public AlmacenRequest(){
    }
    public AlmacenRequest(String id, Almacen almacen){
        this.id = id;
        this.almacen = almacen;
    }
    public String getId(){
        return this.id;
    }

    public String setId(String id){
        return this.id = id;
    }

    public Almacen getAlmacen(){
        return this.almacen;
    }

    public Almacen setAlmacen(Almacen almacen){
        return this.almacen = almacen;
    }
}
