package com.example.hotelstaclara.model;

import java.util.Date;

enum estado_reservacion{
    activa,
    pagada,
    cancelada;
}

public class reservacion {

    private int id_reservacion;
    private Date fecha_reserva;
    private Date fecja_ingreso;
    private Date fecha_salida;
    private int id_cliente;
    private int id_empleado;
    private int id_habitacion;
    private estado_reservacion estado_reservacion;

    public reservacion() {
    }

    public reservacion(int id_reservacion, Date fecha_reserva, Date fecja_ingreso, Date fecha_salida, int id_cliente, int id_empleado, int id_habitacion, com.example.hotelstaclara.model.estado_reservacion estado_reservacion) {
        this.id_reservacion = id_reservacion;
        this.fecha_reserva = fecha_reserva;
        this.fecja_ingreso = fecja_ingreso;
        this.fecha_salida = fecha_salida;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.id_habitacion = id_habitacion;
        this.estado_reservacion = estado_reservacion;
    }

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

    public Date getFecja_ingreso() {
        return fecja_ingreso;
    }

    public void setFecja_ingreso(Date fecja_ingreso) {
        this.fecja_ingreso = fecja_ingreso;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
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

    public com.example.hotelstaclara.model.estado_reservacion getEstado_reservacion() {
        return estado_reservacion;
    }

    public void setEstado_reservacion(com.example.hotelstaclara.model.estado_reservacion estado_reservacion) {
        this.estado_reservacion = estado_reservacion;
    }
}
