package com.example.hotelstaclara.controllers.UserControllers;

import com.example.hotelstaclara.Recursos.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class USERreservaciones {

    @FXML
    private Button bnt_Buscar;

    @FXML
    private Button btn_Clientes;

    @FXML
    private Button btn_Eliminar;

    @FXML
    private Button btn_Habitaciones;

    @FXML
    private Button btn_Pagos;

    @FXML
    private Button btn_Reservaciones;

    @FXML
    private Button but_agregar;

    @FXML
    private Button but_editar;

    @FXML
    private TextField txt_Buscador;

    Rutas ruta = new Rutas();

    @FXML
    void btn_Buscar(ActionEvent event) {

    }

    @FXML
    void btn_Clientes(ActionEvent event) {
        ruta.pasarRutasRecepcionista("USERclientes", btn_Clientes);
    }

    @FXML
    void btn_Eliminar(ActionEvent event) {

    }

    @FXML
    void btn_Habitaciones(ActionEvent event) {
        ruta.pasarRutasRecepcionista("USERhabitaciones", btn_Habitaciones);
    }

    @FXML
    void btn_Pagos(ActionEvent event) {
        ruta.pasarRutasRecepcionista("USERpagos", btn_Pagos);
    }

    @FXML
    void btn_Reservaciones(ActionEvent event) {

    }

    //------------------------

    @FXML
    void but_agregar(ActionEvent event) {
        ruta.pasarRutasRecepcionistaFroms("formReservacion", but_agregar);
    }

    @FXML
    void but_editar(ActionEvent event) {

    }

    @FXML
    void txt_Buscar(ActionEvent event) {

    }

    public void PanelLogo_Click(MouseEvent mouseEvent) {
        ruta.pasarRutasLogin("Login", btn_Pagos);
    }
}
