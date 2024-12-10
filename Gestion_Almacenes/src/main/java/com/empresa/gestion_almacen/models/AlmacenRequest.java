package com.empresa.gestion_almacen.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AlmacenRequest{
    @JsonProperty ("id") private String id;
    @JsonProperty ("almacen") private Almacen almacen;
    public AlmacenRequest(){
    }
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
