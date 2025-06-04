package com.example.hotelstaclara.controllers.AdminController;

import com.example.hotelstaclara.Alert.Alert;
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

public class AdminPagosController {
    public static int id_pago = -1;

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
    public void initialize() {
        llenarTabla();
    }


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




    public void but_login(javafx.scene.input.MouseEvent mouseEvent) {
        ruta.pasarRutasLogin("Login", but_Cliente);
    }

    public void but_reservaciones(MouseEvent mouseEvent) {

        if (tabla_pagos.getSelectionModel().getSelectedItem() == null) {
            MesajesAlert mesajesAlert = new MesajesAlert();
            mesajesAlert.mostarAlertError("Seleccione una reservacion");
            return;
        }
        id_pago = tabla_pagos.getSelectionModel().getSelectedItem().getId_pago();
        ruta.pasarRutasAdminFroms("formPagoReservacion", but_reservaciones);
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
}
