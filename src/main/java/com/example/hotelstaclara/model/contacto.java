package com.example.hotelstaclara.model;

public class contacto {
    private int id_contacto;
    private String telefono_1;
    private String direccion;

    public contacto() {
    }

    public contacto(String telefono_1, String direccion) {
        this.telefono_1 = telefono_1;
        this.direccion = direccion;
    }

    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public String getTelefono_1() {
        return telefono_1;
    }

    public void setTelefono_1(String telefono_1) {
        this.telefono_1 = telefono_1;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
