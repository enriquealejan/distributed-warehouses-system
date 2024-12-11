package com.empresa.gestion_almacen.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "registros") // Nombre de la colección en MongoDB
public class Registro {

    @Id
    private String id; // MongoDB usa identificadores tipo String

    @Field("producto_id") // Referencia al producto por ID
    @JsonProperty("productoId") // Jackson: mapea el campo JSON "productoId" con este atributo
    private String productoId;

    @Field("tipo_movimiento") // Ejemplo: "ENTRADA", "SALIDA"
    private String tipoMovimiento;

    @Field("cantidad") // Cantidad de producto afectado
    private int cantidad;

    @Field("fecha_movimiento") // Fecha del movimiento
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaMovimiento;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("productoId") // Jackson: mapea con "productoId"
    public String getIdProducto() {
        return productoId;
    }

    @JsonProperty("productoId") // Jackson: mapea con "productoId"
    public void setIdProducto(String idProducto) {
        this.productoId = idProducto;
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
