package com.example.hotelstaclara.model;

public class email {

    private int id_email;
    private String email;
    private int id_cliente;
    private int id_empleado;

    public email() {
    }

    public email(int id_email, String email, int id_cliente, int id_empleado) {
        this.id_email = id_email;
        this.email = email;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
    }

    public int getId_email() {
        return id_email;
    }

    public void setId_email(int id_email) {
        this.id_email = id_email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
}
