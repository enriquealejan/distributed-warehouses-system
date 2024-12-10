package com.empresa.gestion_almacen.models;

public class RegistroRequest {
    private String id;
    private Registro registro;
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
