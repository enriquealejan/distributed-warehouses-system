package com.empresa.gestion_almacen.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "registros") // Nombre de la colección en MongoDB
public class Registro {

    @Id
    private String id; // MongoDB usa identificadores tipo String

    @Field("producto_id") // Referencia al producto por ID
    private String idProducto;

    @Field("tipo_movimiento") // Ejemplo: "ENTRADA", "SALIDA"
    private String tipoMovimiento;

    @Field("cantidad") // Cantidad de producto afectado
    private int cantidad;

    @Field("fecha_movimiento") // Fecha del movimiento
    private LocalDateTime fechaMovimiento;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
}
