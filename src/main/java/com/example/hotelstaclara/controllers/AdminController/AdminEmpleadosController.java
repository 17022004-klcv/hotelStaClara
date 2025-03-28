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

public class AdminEmpleadosController {

    @FXML
    private Button but_Cliente;

    @FXML
    private Button but_Habitaciones;

    @FXML
    private Button but_Pago;

    @FXML
    private Button but_Reservaciones;


    @FXML
    private Button but_agregar;

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
    void but_Habitaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminHabitaciones", but_Habitaciones);
    }

    @FXML
    void but_Pago(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminPagos", but_Pago);
    }

    @FXML
    void but_Reservaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminReservaciones" , but_Reservaciones);
    }

    // metodos para ir a formularios

    @FXML
    void but_agregar(ActionEvent event) {
    ruta.pasarRutasAdminFroms("formAddEmpleado", but_agregar);
    }

    @FXML
    void but_editar(ActionEvent event) {

    }

    @FXML
    void but_eliminar(ActionEvent event) {

    }


}
