package com.example.hotelstaclara.model;

import java.util.Date;

public class cliente_membresia {

    private int id_cliente_membresia;
    private int id_cliente;
    private int id_membresia;
    private Date fecha_inicio;
    private Date fecha_fin;


    public cliente_membresia() {
    }

    public cliente_membresia(int id_cliente_membresia, int id_cliente, int id_membresia, Date fecha_inicio, Date fecha_fin) {
        this.id_cliente_membresia = id_cliente_membresia;
        this.id_cliente = id_cliente;
        this.id_membresia = id_membresia;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public int getId_cliente_membresia() {
        return id_cliente_membresia;
    }

    public void setId_cliente_membresia(int id_cliente_membresia) {
        this.id_cliente_membresia = id_cliente_membresia;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_membresia() {
        return id_membresia;
    }

    public void setId_membresia(int id_membresia) {
        this.id_membresia = id_membresia;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}
