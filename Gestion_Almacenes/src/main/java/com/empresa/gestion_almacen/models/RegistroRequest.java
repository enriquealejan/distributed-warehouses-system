package com.empresa.gestion_almacen.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistroRequest {

    private String id;
    private Registro registro;

    // Constructor por defecto (necesario para deserializaciÃ³n)
    public RegistroRequest() {
    }

    // Constructor adicional opcional
    public RegistroRequest(String id, Registro registro) {
        this.id = id;
        this.registro = registro;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }
}
