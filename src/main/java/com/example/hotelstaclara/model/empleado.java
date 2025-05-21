package com.example.hotelstaclara.model;

public class empleado {

    private int id_empleado;
    private String nombre_empleado;
    private String apellido_empleado;
    private String DUI_empleado;
    private int id_contacto;
    private int id_cargo;
    private int estado_empleado;
    public empleado() {
    }

    public empleado(String nombre_empleado, String apellido_empleado, String DUI_empleado, int id_contacto, int id_cargo, int estado_empleado) {
        this.nombre_empleado = nombre_empleado;
        this.apellido_empleado = apellido_empleado;
        this.DUI_empleado = DUI_empleado;
        this.id_contacto = id_contacto;
        this.id_cargo = id_cargo;
        this.estado_empleado = estado_empleado;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getApellido_empleado() {
        return apellido_empleado;
    }

    public void setApellido_empleado(String apellido_empleado) {
        this.apellido_empleado = apellido_empleado;
    }

    public String getDUI_empleado() {
        return DUI_empleado;
    }

    public void setDUI_empleado(String DUI_empleado) {
        this.DUI_empleado = DUI_empleado;
    }

    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public int getEstado_empleado() {
        return estado_empleado;
    }

    public void setEstado_empleado(int estado_empleado) {
        this.estado_empleado = estado_empleado;
    }
}
