package com.example.hotelstaclara.model;

public class Factura {
    private int id_factura;
    private int id_pago;

    public Factura() {
    }

    public Factura(int id_factura, int id_pago) {
        this.id_factura = id_factura;
        this.id_pago = id_pago;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }
}
