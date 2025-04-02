package com.example.hotelstaclara.controllers.AdminController;

import com.example.hotelstaclara.Recursos.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminHabitacionesController {

    @FXML
    private Button but_Clientes;

    @FXML
    private Button but_Empleados;

    @FXML
    private Button but_Pagos;

    @FXML
    private Button but_Reservaciones;

    @FXML
    private Button bt_agregar;

    @FXML
    private Button bt_editar;

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
    void but_Reservaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminReservaciones" , but_Reservaciones);

    }

    //----------------
    @FXML
    void bt_agregar(ActionEvent event) {
    ruta.pasarRutasAdminFroms("formHabitaciones", bt_agregar);
    }

    @FXML
    void bt_editar(ActionEvent event) {

    }

}
