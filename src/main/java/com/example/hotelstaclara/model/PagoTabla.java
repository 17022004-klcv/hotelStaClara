package com.example.hotelstaclara.model;

public class PagoTabla {
    private String cliente;
    private String empleado;
    private String habitacion;
    private String estado;
    private double monto;

    private  int id_pago;
    private int id_reservacion;


    public PagoTabla(String cliente, String empleado, String habitacion, String estado, double monto, int id_pago, int id_reservacion) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.habitacion = habitacion;
        this.estado = estado;
        this.monto = monto;
        this.id_pago = id_pago;
        this.id_reservacion = id_reservacion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }
}
