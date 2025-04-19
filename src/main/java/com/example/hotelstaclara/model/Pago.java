package com.example.hotelstaclara.model;

import java.util.Date;

public class Pago {

    private int id_pago;
    private double monto;
    private double monto_con_descuento;
    private Date fecha_pago;
    private String metodo_de_pago;
    private int id_reservacion;
    private int id_cliente;

    public Pago() {
    }

    public Pago(int id_pago, double monto, double monto_con_descuento, Date fecha_pago, String metodo_de_pago, int id_reservacion, int id_cliente) {
        this.id_pago = id_pago;
        this.monto = monto;
        this.monto_con_descuento = monto_con_descuento;
        this.fecha_pago = fecha_pago;
        this.metodo_de_pago = metodo_de_pago;
        this.id_reservacion = id_reservacion;
        this.id_cliente = id_cliente;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getMonto_con_descuento() {
        return monto_con_descuento;
    }

    public void setMonto_con_descuento(double monto_con_descuento) {
        this.monto_con_descuento = monto_con_descuento;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getMetodo_de_pago() {
        return metodo_de_pago;
    }

    public void setMetodo_de_pago(String metodo_de_pago) {
        this.metodo_de_pago = metodo_de_pago;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
