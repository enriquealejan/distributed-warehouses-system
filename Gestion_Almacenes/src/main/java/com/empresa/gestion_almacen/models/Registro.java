package com.empresa.gestion_almacen.models;

public class Registro {
    private int id;
    private int idProducto;
    private int idAlmacen;
    private int cantidad;
    private String fecha;

    // Constructor
    public Registro(int id, int idProducto, int idAlmacen, int cantidad, String fecha) {
        this.id = id;
        this.idProducto = idProducto;
        this.idAlmacen = idAlmacen;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}


