package com.example.hotelstaclara.controllers.UserControllers;

import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.database.PagoDAO;
import com.example.hotelstaclara.model.PagoTabla;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

public class USERpagos {

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

    @FXML
    private Button but_menbrecia;

    @FXML
    private Button but_reservaciones;

    @FXML
    private TableView<PagoTabla> tabla_pagos;

    @FXML
    private TableColumn<PagoTabla, String> colum_cliente;

    @FXML
    private TableColumn<PagoTabla, String> colum_empleado;

    @FXML
    private TableColumn<PagoTabla, String> colum_habitacion;

    @FXML
    private TableColumn<PagoTabla, String> colum_estado;

    @FXML
    private TableColumn<PagoTabla, Double> colum_monto;
    Rutas ruta = new Rutas();
    public static int id_pagoReservacion = -1;

    public void initialize() {
        llenarTabla();
    }

    @FXML
    void btn_Clientes(ActionEvent event) {
    ruta.pasarRutasRecepcionista("USERclientes", btn_Clientes);
    }

    @FXML
    void btn_Habitaciones(ActionEvent event) {
    ruta.pasarRutasRecepcionista("USERhabitaciones", btn_Habitaciones);
    }

    @FXML
    void btn_Pagos(ActionEvent event) {

    }

    @FXML
    void btn_Reservaciones(ActionEvent event) {

    }

    // ------------------------------------
    @FXML
    void but_editar(ActionEvent event) {
    }

    @FXML
    void but_menbrecia(ActionEvent event) {
        ruta.pasarRutasRecepcionistaFroms("formPagoMembresia", but_menbrecia);
    }

    public void PanelLogoClick(MouseEvent mouseEvent) {
        ruta.pasarRutasLogin("Login", btn_Pagos);
    }

    public void llenarTabla() {
        PagoDAO pagoDAO = new PagoDAO();
        colum_cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colum_empleado.setCellValueFactory(new PropertyValueFactory<>("empleado"));
        colum_habitacion.setCellValueFactory(new PropertyValueFactory<>("habitacion"));
        colum_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colum_monto.setCellValueFactory(new PropertyValueFactory<>("monto"));

        tabla_pagos.setItems(FXCollections.observableArrayList(pagoDAO.traerPagoTabla()));
    }


    public void but_pagarRedervaciones(MouseEvent mouseEvent) {
        if (tabla_pagos.getSelectionModel().getSelectedItem() == null) {
            MesajesAlert mesajesAlert = new MesajesAlert();
            mesajesAlert.mostarAlertError("Seleccione una reservacion");
            return;
        }
        id_pagoReservacion = tabla_pagos.getSelectionModel().getSelectedItem().getId_pago();
        ruta.pasarRutasAdminFroms("formPagoReservacion", but_reservaciones);
    }
}
