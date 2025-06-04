package com.example.hotelstaclara.model;

public class FacturaReservacionPago {

    private int idReservacion;
    private String nombreCliente;
    private String nombreEmpleado;
    private double precioHabitacion;
    private String nombreMembresia;
    private double descuento;
    private double montoTotalPagar;
    private int idCliente;
    private int idHabitacion;


    // Constructor, getters y setters
    public FacturaReservacionPago(int idReservacion, String nombreCliente, String nombreEmpleado,
                                  double precioHabitacion, String nombreMembresia, double descuento,
                                  double montoTotalPagar, int idCliente, int idHabitacion) {
        this.idReservacion = idReservacion;
        this.nombreCliente = nombreCliente;
        this.nombreEmpleado = nombreEmpleado;
        this.precioHabitacion = precioHabitacion;
        this.nombreMembresia = nombreMembresia;
        this.descuento = descuento;
        this.montoTotalPagar = montoTotalPagar;
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
    }

    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public double getPrecioHabitacion() {
        return precioHabitacion;
    }

    public void setPrecioHabitacion(double precioHabitacion) {
        this.precioHabitacion = precioHabitacion;
    }

    public String getNombreMembresia() {
        return nombreMembresia;
    }

    public void setNombreMembresia(String nombreMembresia) {
        this.nombreMembresia = nombreMembresia;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getMontoTotalPagar() {
        return montoTotalPagar;
    }

    public void setMontoTotalPagar(double montoTotalPagar) {
        this.montoTotalPagar = montoTotalPagar;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }
}