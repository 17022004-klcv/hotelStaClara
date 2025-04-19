package com.example.hotelstaclara.model;

enum estado_habitacion {
    disponible,
    ocupada,
    sucia
}


public class habitacion {
    private int id_habitacion;
    private String numero_habitacion;
    private String tipo_habitacion;
    private int capacidad;
    private double precio;
    private estado_habitacion estado_habitacion;

    public habitacion() {
    }

    public habitacion(int id_habitacion, String numero_habitacion, String tipo_habitacion, int capacidad, double precio, estado_habitacion estado_habitacion) {
        this.id_habitacion = id_habitacion;
        this.numero_habitacion = numero_habitacion;
        this.tipo_habitacion = tipo_habitacion;
        this.capacidad = capacidad;
        this.precio = precio;
        this.estado_habitacion = estado_habitacion;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public String getNumero_habitacion() {
        return numero_habitacion;
    }

    public void setNumero_habitacion(String numero_habitacion) {
        this.numero_habitacion = numero_habitacion;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public estado_habitacion getEstado_habitacion() {
        return estado_habitacion;
    }

    public void setEstado_habitacion(estado_habitacion estado_habitacion) {
        this.estado_habitacion = estado_habitacion;
    }
}





