package com.example.hotelstaclara.model;

public enum Estado_reservaciones {
    activa,
    pagada,
    cancelada;

    @Override
    public String toString() {
        return super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1);
    }
}
