package com.example.hotelstaclara.model;

import java.sql.Date;

public class Reservaciones {
    private int id_reservacion;
    private java.sql.Date fecha_reserva;
    private java.sql.Date fecha_ingreso;
    private java.sql.Date fecha_salida;
    private int id_cliente;
    private int id_empleado;
    private int id_habitacion;
    private Estado_reservaciones estado_reservacion;

    // variables auxiliares
    private String nombre_cliente;
    private String nombre_empleado;
    private String numero_habitacion;
    // Constructor
    public Reservaciones() {
    }

    public Reservaciones(int id_reservacion, Date fecha_reserva, Date fecha_ingreso, Date fecha_salida, int id_cliente, int id_empleado, int id_habitacion, Estado_reservaciones estado_reservacion) {
        this.id_reservacion = id_reservacion;
        this.fecha_reserva = fecha_reserva;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_salida = fecha_salida;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.id_habitacion = id_habitacion;
        this.estado_reservacion = estado_reservacion;
    }

//    public Reservaciones(Date fecha_reserva, Date fecha_ingreso, Date fecha_salida, String nombre_cliente, String nombre_empleado, String numero_habitacion, int id_reservacion) {
//        this.fecha_reserva = fecha_reserva;
//        this.fecha_ingreso = fecha_ingreso;
//        this.fecha_salida = fecha_salida;
//        this.nombre_cliente = nombre_cliente;
//        this.nombre_empleado = nombre_empleado;
//        this.numero_habitacion = numero_habitacion;
//        this.id_reservacion = id_reservacion;
//    }

    // Getters y setters

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecja_ingreso(Date fecja_ingreso) {
        this.fecha_ingreso = fecja_ingreso;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Estado_reservaciones getEstado_reservacion() {
        return estado_reservacion;
    }

    public void setEstado_reservacion(Estado_reservaciones estado_reservacion) {
        this.estado_reservacion = estado_reservacion;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
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

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    // Getters y setters auxiliares


    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getNumero_habitacion() {
        return numero_habitacion;
    }

    public void setNumero_habitacion(String numero_habitacion) {
        this.numero_habitacion = numero_habitacion;
    }
}
