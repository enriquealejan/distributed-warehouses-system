package com.empresa.gestion_almacen.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistroRequest {
    @JsonProperty ("id") private String id;
    @JsonProperty ("registro") private Registro registro;

    public RegistroRequest(String id, Registro registro){
        this.id = id;
        this.registro = registro;
    }
    public String getId(){
        return this.id;
    }

    public Registro getRegistro(){
        return this.registro;
    }
}
