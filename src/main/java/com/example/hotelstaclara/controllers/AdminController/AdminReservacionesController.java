package com.example.hotelstaclara.controllers.AdminController;

import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.database.ReservacionesDAO;
import com.example.hotelstaclara.model.Reservaciones;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @FXML
    private TableColumn<Reservaciones, Integer> colum_cliente;

    @FXML
    private TableColumn<Reservaciones, Integer> colum_empleado;

    @FXML
    private TableColumn<Reservaciones, String> colum_fechaReserva;

    @FXML
    private TableColumn<Reservaciones, String> colum_fechaRetiro;

    @FXML
    private TableColumn<Reservaciones, String> colum_fechaingreso;

    @FXML
    private TableColumn<Reservaciones, Integer> colum_habitacion;

    @FXML
    private TableColumn<Reservaciones, Integer> colum_id;

    @FXML
    private TableView<Reservaciones> tabla_reservacion;

    Rutas ruta = new Rutas();

    public void initialize() {
        llenarTablaReservaciones();
    }

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

    public void but_login(javafx.scene.input.MouseEvent mouseEvent) {
        ruta.pasarRutasLogin("Login", but_agregar);
    }

    //--------------------------------------------------------------------------------------------------------------------



    @FXML
    void but_editar(ActionEvent event) {
        MesajesAlert mesajesAlert = new MesajesAlert();
        Reservaciones filaSeleccionada = tabla_reservacion.getSelectionModel().getSelectedItem();
        if (filaSeleccionada == null) {
            mesajesAlert.mostarAlertError("Seleccione una fila");
            return;
        }
        ruta.setGetController(filaSeleccionada);
        ruta.setOpAddEdit("Edit");
        ruta.pasarRutasAdminFroms("formReservaciones", but_agregar);
    }



    @FXML
    void but_eliminar(ActionEvent event) {

    }


    @FXML
    void but_agregar(ActionEvent event) {
        ruta.pasarRutasAdminFroms("formReservaciones", but_agregar);
    }

    public void llenarTablaReservaciones(){

        colum_id.setCellValueFactory(new PropertyValueFactory<>("id_reservacion"));
        colum_cliente.setCellValueFactory(new PropertyValueFactory<>("nombreClienteCopleto"));
        colum_empleado.setCellValueFactory(new PropertyValueFactory<>("nombre_empleado"));
        colum_fechaReserva.setCellValueFactory(new PropertyValueFactory<>("fecha_reserva"));
        colum_fechaRetiro.setCellValueFactory(new PropertyValueFactory<>("fecha_salida"));
        colum_fechaingreso.setCellValueFactory(new PropertyValueFactory<>("fecha_ingreso"));
        colum_habitacion.setCellValueFactory(new PropertyValueFactory<>("numero_habitacion"));

        tabla_reservacion.setItems(FXCollections.observableArrayList(ReservacionesDAO.traerReservaciones()));
    }
}

