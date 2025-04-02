package com.example.hotelstaclara.controllers.AdminController;

import com.example.hotelstaclara.Recursos.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminReservacionesController {

    @FXML
    private Button but_Clientes;

    @FXML
    private Button but_Empleados;

    @FXML
    private Button but_Habitaciones;

    @FXML
    private Button but_Pagos;


    @FXML
    private Button but_agregar;

    @FXML
    private Button but_editar;

    @FXML
    private Button but_eliminar;

    Rutas ruta = new Rutas();

    @FXML
    void but_Clientes(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminClientes", but_Clientes);
    }

    @FXML
    void but_Empleados(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminEmpleados", but_Empleados);
    }

    @FXML
    void but_Pagos(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminPagos", but_Pagos);
    }

    @FXML
    void but_Habitaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminHabitaciones", but_Habitaciones);
    }

    //----------------------------


    @FXML
    void but_agregar(ActionEvent event) {
    ruta.pasarRutasAdminFroms("formReservaciones", but_agregar);
    }

    @FXML
    void but_editar(ActionEvent event) {

    }

    @FXML
    void but_eliminar(ActionEvent event) {

    }

}
