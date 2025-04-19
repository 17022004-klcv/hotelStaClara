package com.example.hotelstaclara.model;

public class cliente {

    private int id_cliente;
    private String nombre_cliente;
    private String apellido_cliente;
    private String DUI_cliente;
    private int id_contacto;
    private int estado_cliente;

    public cliente() {

    }

    public cliente(int id_cliente, String nombre_cliente, String apellido_cliente, String DUI_cliente, int id_contacto, int estado_cliente) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.DUI_cliente = DUI_cliente;
        this.id_contacto = id_contacto;
        this.estado_cliente = estado_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public String getDUI_cliente() {
        return DUI_cliente;
    }

    public void setDUI_cliente(String DUI_cliente) {
        this.DUI_cliente = DUI_cliente;
    }

    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public int getEstado_cliente() {
        return estado_cliente;
    }

    public void setEstado_cliente(int estado_cliente) {
        this.estado_cliente = estado_cliente;
    }
}
