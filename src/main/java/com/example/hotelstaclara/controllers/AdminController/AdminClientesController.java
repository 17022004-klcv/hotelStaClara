package com.example.hotelstaclara.controllers.AdminController;

import com.example.hotelstaclara.model.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminClientesController {

    @FXML
    private Button but_Empleados;

    @FXML
    private Button but_Habbitaciones;

    @FXML
    private Button but_Pagos;

    @FXML
    private Button but_Reservaciones;

    @FXML
    private Button but_agragar;

    @FXML
    private Button but_editar;

    @FXML
    private Button but_eliminar;

    Rutas ruta = new Rutas();

    @FXML
    void but_Empleados(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminEmpleados", but_Empleados);
    }

    @FXML
    void but_Habbitaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminHabitaciones", but_Habbitaciones);
    }

    @FXML
    void but_Pagos(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminPagos", but_Pagos);
    }

    @FXML
    void but_Reservaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminReservaciones" , but_Reservaciones);
    }

    // metodos para pasar alas vista de los formilarios

    @FXML
    void but_agragar(ActionEvent event) {
    ruta.pasarRutasAdminFroms("formAddCliente", but_agragar);
    }

    @FXML
    void but_editar(ActionEvent event) {

    }

    @FXML
    void but_eliminar(ActionEvent event) {

    }
}
