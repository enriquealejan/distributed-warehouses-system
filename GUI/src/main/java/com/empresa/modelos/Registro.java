package com.empresa.modelos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class Registro {
    private String id;

    @JsonProperty("productoId")
    private String productoId;
    
    private String tipoMovimiento; // Puede ser "ENTRADA" o "SALIDA"
    private int cantidad;
    private LocalDateTime fechaMovimiento;

    public Registro() {
    }

    public Registro(String id, String productoId, String tipoMovimiento, int cantidad, LocalDateTime  fechaMovimiento) {
        this.id = id;
        this.productoId = productoId;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProducto() {
        return productoId;
    }

    public void setIdProducto(String idProducto) {
        System.out.println(idProducto);
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

    public void setFechaMovimiento(LocalDateTime  fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "id='" + id + '\'' +
                ", idProducto='" + productoId + '\'' +
                ", tipoMovimiento='" + tipoMovimiento + '\'' +
                ", cantidad=" + cantidad +
                ", fechaMovimiento=" + fechaMovimiento +
                '}';
    }
}
