package com.example.hotelstaclara.model;

public class membresia {

    private int id_membresia;
    private String nombre_membresia;
    private String descripcion;
    private double descuento;
    private int estado_membresia;
    private double costo_membresia;

    public membresia() {
    }

    public membresia(int id_membresia, String nombre_membresia, String descripcion, double descuento, int estado_membresia, double costo_membresia) {
        this.id_membresia = id_membresia;
        this.nombre_membresia = nombre_membresia;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.estado_membresia = estado_membresia;
        this.costo_membresia = costo_membresia;
    }

    public int getId_membresia() {
        return id_membresia;
    }

    public void setId_membresia(int id_membresia) {
        this.id_membresia = id_membresia;
    }

    public String getNombre_membresia() {
        return nombre_membresia;
    }

    public void setNombre_membresia(String nombre_membresia) {
        this.nombre_membresia = nombre_membresia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getEstado_membresia() {
        return estado_membresia;
    }

    public void setEstado_membresia(int estado_membresia) {
        this.estado_membresia = estado_membresia;
    }

    public double getCosto_membresia() {
        return costo_membresia;
    }

    public void setCosto_membresia(double costo_membresia) {
        this.costo_membresia = costo_membresia;
    }
}
