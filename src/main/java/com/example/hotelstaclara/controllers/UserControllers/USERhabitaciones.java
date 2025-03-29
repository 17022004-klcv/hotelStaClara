package com.example.hotelstaclara.controllers.UserControllers;

import com.example.hotelstaclara.model.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class USERhabitaciones {

    @FXML
    private Button btn_Clientes;

    @FXML
    private Button btn_Habitaciones;

    @FXML
    private Button btn_Pagos;

    @FXML
    private Button btn_Reservaciones;

    @FXML
    private Button but_editar;

    Rutas ruta = new Rutas();

    @FXML
    void btn_Clientes(ActionEvent event) {
        ruta.pasarRutasRecepcionista("USERclientes", btn_Clientes);
    }

    @FXML
    void btn_Habitaciones(ActionEvent event) {

    }

    @FXML
    void btn_Pagos(ActionEvent event) {
        ruta.pasarRutasRecepcionista("USERpagos", btn_Pagos);
    }

    @FXML
    void btn_Reservaciones(ActionEvent event) {
        ruta.pasarRutasRecepcionista("USERreservaciones", btn_Reservaciones);
    }

    @FXML
    void but_editar(ActionEvent event) {
    ruta.pasarRutasRecepcionistaFroms("formHabitacion", but_editar);
    }

}
