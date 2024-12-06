package com.empresa.gestion_almacen.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Clave primaria autogenerada
    private int id;
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false) // Relación con Producto
    private Long idProducto;
    @Column(name = "tipo_movimiento", nullable = false) // Ejemplo: "ENTRADA", "SALIDA"
    private String tipoMovimiento;
    @Column(name = "cantidad", nullable = false) // Cantidad de producto afectado
    private int cantidad;
    @Column(name = "fecha_movimiento", nullable = false) // Fecha del movimiento
    private LocalDateTime fechaMovimiento;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
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


