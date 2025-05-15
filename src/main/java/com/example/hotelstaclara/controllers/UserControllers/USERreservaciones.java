package com.example.hotelstaclara.controllers.UserControllers;

import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.database.HabiracionDAO;
import com.example.hotelstaclara.database.ReservacionesDAO;
import com.example.hotelstaclara.model.Reservaciones;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class USERreservaciones {

    @FXML
    private ImageView PanelLogo;

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
    private TableView<Reservaciones> tabla_reservacion;

    @FXML
    private TextField txt_Buscador;

    Rutas ruta = new Rutas();


    public void initialize() {
        llenarTablaReservaciones();
    }

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

    public void llenarTablaReservaciones(){

        colum_cliente.setCellValueFactory(new PropertyValueFactory<>("nombre_cliente"));
        colum_empleado.setCellValueFactory(new PropertyValueFactory<>("nombre_empleado"));
        colum_fechaReserva.setCellValueFactory(new PropertyValueFactory<>("fecha_reserva"));
        colum_fechaRetiro.setCellValueFactory(new PropertyValueFactory<>("fecha_salida"));
        colum_fechaingreso.setCellValueFactory(new PropertyValueFactory<>("fecha_ingreso"));
        colum_habitacion.setCellValueFactory(new PropertyValueFactory<>("numero_habitacion"));

        tabla_reservacion.setItems(FXCollections.observableArrayList(ReservacionesDAO.traerReservaciones()));
    }
}
