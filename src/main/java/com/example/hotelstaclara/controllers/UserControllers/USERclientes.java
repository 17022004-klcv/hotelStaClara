package com.example.hotelstaclara.controllers.UserControllers;

import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.controllers.formsAdminControllers.formAddClienteController;
import com.example.hotelstaclara.controllers.formsUserControlllers.FormAddClienteController;
import com.example.hotelstaclara.database.clienteDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class USERclientes {

    @FXML
    private Button bnt_Buscar;

    @FXML
    private Button btn_Clientes;

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
    private TableColumn<Map, Object> columnCelular;

    @FXML
    private TableView<Map> tableClients;

    @FXML
    public void initialize() throws SQLException {
        llenarTabla();
    }

    Rutas ruta = new Rutas();
    @FXML
    void btn_Buscar(ActionEvent event) {

    }

    @FXML
    void btn_Clientes(ActionEvent event) {

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
        ruta.pasarRutasRecepcionista("USERreservaciones", btn_Reservaciones);
    }
// -----------------------------
    @FXML
    void but_agregar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/hotelstaclara/views/formsUserViews/formAddCliente.fxml"));
        Parent root = loader.load();

        FormAddClienteController controlador = loader.getController();
        controlador.setOnSuccessCallback(() -> {
            try {
                llenarTabla();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Agregar Cliente");
        stage.show();
    }

    @FXML
    void but_editar(ActionEvent event) {

        try {
            Map<String, Object> seleccionado = tableClients.getSelectionModel().getSelectedItem();

            if (seleccionado == null) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Advertencia");
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor, selecciona un cliente primero.");
                alerta.showAndWait();
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/hotelstaclara/views/formsUserViews/formAddCliente.fxml"));
            Parent root = loader.load();

            String nombre = (String) seleccionado.get("c.nombre_cliente");
            String apellido = (String) seleccionado.get("c.apellido_cliente");
            String telefono = (String) seleccionado.get("ct.telefono_1");
            String celular = (String) seleccionado.get("ct.telefono_2");
            String direccion = (String) seleccionado.get("ct.direccion");
            String DUI = (String) seleccionado.get("c.DUI_cliente");
            String email = (String) seleccionado.get("e.email");

            FormAddClienteController controlador = loader.getController();
            controlador.setDatos(nombre, apellido, telefono, celular, direccion, DUI, email);
            controlador.setButton("Editar");
            controlador.setOnSuccessCallback(() -> {
                try {
                    llenarTabla();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            // Mostrar ventana
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Cliente");
            stage.show();

            llenarTabla();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txt_Buscar(ActionEvent event) {

    }

    public void PanelLogo_Click(MouseEvent mouseEvent) {
        ruta.pasarRutasLogin("Login", btn_Pagos);
    }

    clienteDAO selectClient = new clienteDAO();
    public void llenarTabla() throws SQLException, SQLException {

        ObservableList<Map> lista = selectClient.getCliente();
        // Limpia datos antiguos
        tableClients.setItems(lista);

        // Configuración de las columnas de la tabla
        columnNombre.setCellValueFactory(new MapValueFactory<>("c.nombre_cliente"));
        columnApellido.setCellValueFactory(new MapValueFactory<>("c.apellido_cliente"));
        columnTelefono.setCellValueFactory(new MapValueFactory<>("ct.telefono_1"));
        columnCelular.setCellValueFactory(new MapValueFactory<>("ct.telefono_2"));
        columnDireccion.setCellValueFactory(new MapValueFactory<>("ct.direccion"));
        columnDui.setCellValueFactory(new MapValueFactory<>("c.DUI_cliente"));
        columnCorreo.setCellValueFactory(new MapValueFactory<>("e.email"));
        columnEstado.setCellValueFactory(new MapValueFactory<>("c.estado_cliente"));

    }
}
