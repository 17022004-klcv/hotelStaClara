package com.example.hotelstaclara.controllers.UserControllers;

import com.example.hotelstaclara.Alert.Alert;
import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.database.HabiracionDAO;
import com.example.hotelstaclara.model.habitacion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.sql.SQLException;

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

    @FXML
    private TableColumn<habitacion, String> colum_cantidadHabitacion;

    @FXML
    private TableColumn<habitacion, String> colum_estadoHabitacion;

    @FXML
    private TableColumn<habitacion, Integer> colum_numeroHabiacion;

    @FXML
    private TableColumn<habitacion, Double> colum_precioHabitacion;

    @FXML
    private TableColumn<habitacion, String> colum_tipoHabitacion;

    @FXML
    private TableView<habitacion> tabla_habitaciones;

    Rutas ruta = new Rutas();

    public void initialize(){
        llenarTablaHabitacion();
        agregarDobleClickEnTabla();
    }


    public void llenarTablaHabitacion(){
        colum_cantidadHabitacion.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        colum_estadoHabitacion.setCellValueFactory(new PropertyValueFactory<>("estado_habitacion"));
        colum_numeroHabiacion.setCellValueFactory(new PropertyValueFactory<>("numero_habitacion"));
        colum_precioHabitacion.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colum_tipoHabitacion.setCellValueFactory(new PropertyValueFactory<>("tipo_habitacion"));
        colum_estadoHabitacion.setCellValueFactory(new PropertyValueFactory<>("estado_habitacion"));

        tabla_habitaciones.setItems(FXCollections.observableArrayList(HabiracionDAO.TraeesHabitacions()));
    }


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
        habitacion hbitacionSeleccionada = tabla_habitaciones.getSelectionModel().getSelectedItem();
        if (hbitacionSeleccionada == null) {
            Alert.showErrorAlert("Error", "Error", "Seleccione una habitacion");
            return;
        }

        ruta.setHabitacion(hbitacionSeleccionada);
        ruta.pasarRutasRecepcionistaFroms("formHabitacion", but_editar);

        ruta.setrecepcionistaController(this);
    }

    public void PanelLogo_Click(MouseEvent mouseEvent) {
        ruta.pasarRutasLogin("Login", btn_Pagos);
    }

    private void agregarDobleClickEnTabla() {
        tabla_habitaciones.setRowFactory(tv -> {
            TableRow<habitacion> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    habitacion habitacion = row.getItem();

                    ruta.setOpAddEdit("AddHabitacion");
                    ruta.setGetController(habitacion);
                    ruta.pasarRutasRecepcionistaFroms("formReservacion", btn_Clientes);
                }
            });

            return row;
        });
    }

}
