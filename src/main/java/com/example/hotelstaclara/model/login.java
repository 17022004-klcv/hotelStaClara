package com.example.hotelstaclara.model;

public class login {
    private int id_login ;
    private int id_empleado;
    private int usuario;
    private int contraseña;

    public login() {
    }
    public login(int id_login, int id_empleado, int usuario, int contraseña) {
        this.id_login = id_login;
        this.id_empleado = id_empleado;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public int getId_login() {
        return id_login;
    }

    public void setId_login(int id_login) {
        this.id_login = id_login;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getContraseña() {
        return contraseña;
    }

    public void setContraseña(int contraseña) {
        this.contraseña = contraseña;
    }

}
