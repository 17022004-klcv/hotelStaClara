package com.example.hotelstaclara.controllers.AdminController;

import com.example.hotelstaclara.Alert.Alert;
import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.database.HabiracionDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import com.example.hotelstaclara.model.habitacion;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    public void initialize() {
        llenarTablaHabitacion();
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




    //----------------

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
        ruta.setOpAddEdit("add");
        ruta.pasarRutasAdminFroms("formHabitaciones", bt_agregar);
    }

    @FXML
    void bt_editar(ActionEvent event) {
        habitacion hbitacionSeleccionada = tabla_habitaciones.getSelectionModel().getSelectedItem();
        if (hbitacionSeleccionada == null) {
            Alert.showErrorAlert("Error", "Error", "Seleccione una habitacion");
            return;
        }

        ruta.setOpAddEdit("edit");
        ruta.setHabitacion(hbitacionSeleccionada);
        ruta.pasarRutasAdminFroms("formHabitaciones", bt_editar);

    }

    public void but_login(javafx.scene.input.MouseEvent mouseEvent) {
        ruta.pasarRutasLogin("Login", bt_agregar);
    }

    public void bt_eliminar(ActionEvent actionEvent) {
    }
}
