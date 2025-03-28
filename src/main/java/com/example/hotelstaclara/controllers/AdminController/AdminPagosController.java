package com.example.hotelstaclara.controllers.AdminController;

import com.example.hotelstaclara.model.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPagosController {

    @FXML
    private Button but_Cliente;

    @FXML
    private Button but_Empreados;

    @FXML
    private Button but_Habitaciones;

    @FXML
    private Button but_Reservaciones;

    @FXML
    private Button but_menbrecia;

    @FXML
    private Button but_reservaciones;

    @FXML
    private Button but_editar;

    @FXML
    private Button but_eliminar;

    Rutas ruta = new Rutas();

    @FXML
    void but_Cliente(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminClientes", but_Cliente);
    }

    @FXML
    void but_Empreados(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminEmpleados", but_Empreados);
    }

    @FXML
    void but_Habitaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminHabitaciones", but_Habitaciones);
    }

    @FXML
    void but_Reservaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminReservaciones" , but_Reservaciones);
    }


    // ---------------------

    @FXML
    void but_editar(ActionEvent event) {

    }

    @FXML
    void but_eliminar(ActionEvent event) {

    }


    @FXML
    void but_menbrecia(ActionEvent event) {
        ruta.pasarRutasAdminFroms("formPagoMembresia", but_menbrecia);
    }

    @FXML
    void but_reservaciones(ActionEvent event) {
    ruta.pasarRutasAdminFroms("formPagoReservacion", but_reservaciones);
    }
}
