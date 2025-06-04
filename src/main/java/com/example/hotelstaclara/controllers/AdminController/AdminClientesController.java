package com.example.hotelstaclara.controllers.AdminController;

import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.controllers.formsAdminControllers.formAddClienteController;
import com.example.hotelstaclara.database.clienteDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
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
    private TableColumn<Map, Object> columnCelular;

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
    void but_agragar(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/hotelstaclara/views/formsAdminViews/formAddCliente.fxml"));
        Parent root = loader.load();

        formAddClienteController controlador = loader.getController();
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
    void but_editar(ActionEvent event) throws IOException {

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
                    "/com/example/hotelstaclara/views/formsAdminViews/formAddCliente.fxml"));
            Parent root = loader.load();

            String nombre = (String) seleccionado.get("c.nombre_cliente");
            String apellido = (String) seleccionado.get("c.apellido_cliente");
            String telefono = (String) seleccionado.get("ct.telefono_1");
            String celular = (String) seleccionado.get("ct.telefono_2");
            String direccion = (String) seleccionado.get("ct.direccion");
            String DUI = (String) seleccionado.get("c.DUI_cliente");
            String email = (String) seleccionado.get("e.email");

            formAddClienteController controlador = loader.getController();
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
    void but_eliminar(ActionEvent event) {

        Map<String, Object> seleccionado = tableClients.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, selecciona un cliente primero.");
            alerta.showAndWait();
            return;
        }



        try {
            String DUI = (String) seleccionado.get("c.DUI_cliente");
            clienteDAO cliente = new clienteDAO();

            cliente.inactivarClientePorDUI(DUI);
            llenarTabla();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Mostrar mensaje al usuario si quieres
            JOptionPane.showMessageDialog(null, "Error al refrescar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void but_login(javafx.scene.input.MouseEvent mouseEvent) {
        ruta.pasarRutasLogin("Login", but_agragar);
    }
    //crear objeto
    clienteDAO selectClient = new clienteDAO();

    //crear metodo para iniciar la tabla
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

    //inicializar tabla
    @FXML
    public void initialize() throws SQLException {
        llenarTabla();
    }

}
