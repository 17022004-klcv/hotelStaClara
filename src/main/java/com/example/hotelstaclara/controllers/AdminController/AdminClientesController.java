package com.example.hotelstaclara.controllers.AdminController;

import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.database.clienteDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

import java.sql.SQLException;
import java.util.Map;

public class AdminClientesController {

    @FXML
    private Button but_Empleados;

    @FXML
    private Button but_Habbitaciones;

    @FXML
    private Button but_Pagos;

    @FXML
    private Button but_Reservaciones;

    @FXML
    private Button but_agragar;

    @FXML
    private Button but_editar;

    @FXML
    private Button but_eliminar;

    @FXML
    private TableColumn<Map, Object> columnApellido;

    @FXML
    private TableColumn<Map, Object> columnCorreo;

    @FXML
    private TableColumn<Map, Object> columnDireccion;

    @FXML
    private TableColumn<Map, Object> columnDui;

    @FXML
    private TableColumn<Map, Object> columnEstado;

    @FXML
    private TableColumn<Map, Object> columnNombre;

    @FXML
    private TableColumn<Map, Object> columnTelefono;

    @FXML
    private TableView<Map> tableClients;

    Rutas ruta = new Rutas();

    @FXML
    void but_Empleados(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminEmpleados", but_Empleados);
    }

    @FXML
    void but_Habbitaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminHabitaciones", but_Habbitaciones);
    }

    @FXML
    void but_Pagos(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminPagos", but_Pagos);
    }

    @FXML
    void but_Reservaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminReservaciones" , but_Reservaciones);
    }

    // metodos para pasar alas vista de los formilarios

    @FXML
    void but_agragar(ActionEvent event) {
    ruta.pasarRutasAdminFroms("formAddCliente", but_agragar);
    }

    @FXML
    void but_editar(ActionEvent event) {

    }
    @FXML
    void but_eliminar(ActionEvent event) {
    }

    public void but_login(javafx.scene.input.MouseEvent mouseEvent) {
        ruta.pasarRutasLogin("Login", but_agragar);
    }
    //crear objeto
    clienteDAO selectClient = new clienteDAO();

    //crear metodo para iniciar la tabla
    public void tableClientes() throws SQLException, SQLException {

        ObservableList<Map> lista = selectClient.getCliente();
        tableClients.setItems(lista);

        // Configuración de las columnas de la tabla
        columnNombre.setCellValueFactory(new MapValueFactory<>("c.nombre_cliente"));
        columnApellido.setCellValueFactory(new MapValueFactory<>("c.apellido_cliente"));
        columnTelefono.setCellValueFactory(new MapValueFactory<>("ct.telefono_1"));
        columnDireccion.setCellValueFactory(new MapValueFactory<>("ct.direccion"));
        columnDui.setCellValueFactory(new MapValueFactory<>("c.DUI_cliente"));
        columnCorreo.setCellValueFactory(new MapValueFactory<>("e.email"));
        columnEstado.setCellValueFactory(new MapValueFactory<>("c.estado_cliente"));

    }

    //inicializar tabla
    @FXML
    public void initialize() throws SQLException {
        tableClientes();
    }

}
