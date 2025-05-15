package com.example.hotelstaclara.controllers.AdminController;

import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.database.empleadoDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class AdminEmpleadosController {

    @FXML
    private TableColumn<Map, Object> ColumnApellido;

    @FXML
    private TableColumn<Map, Object> ColumnCargo;

    @FXML
    private TableColumn<Map, Object> ColumnCorreo;

    @FXML
    private TableColumn<Map, Object> ColumnDUI;

    @FXML
    private TableColumn<Map, Object> ColumnDireccion;

    @FXML
    private TableColumn<Map, Object> ColumnEstado;

    @FXML
    private TableColumn<Map, Object> ColumnNombre;

    @FXML
    private TableColumn<Map, Object> ColumnTelefono;

    @FXML
    private TableView<Map> TablaEmpleado;

    @FXML
    private Button but_Cliente;

    @FXML
    private Button but_Habitaciones;

    @FXML
    private Button but_Pago;

    @FXML
    private Button but_Reservaciones;

    @FXML
    private Button but_agregar;

    @FXML
    private Button but_editar;

    @FXML
    private Button but_eliminar;

    Rutas ruta = new Rutas();
    MesajesAlert alert=new MesajesAlert();
    public static String Estado_boton = "", Email_seleccionado = "";

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        Filltable();
    }

    @FXML
    void but_Cliente(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminClientes", but_Cliente);
    }

    @FXML
    void but_Habitaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminHabitaciones", but_Habitaciones);
    }

    @FXML
    void but_Pago(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminPagos", but_Pago);
    }

    @FXML
    void but_Reservaciones(ActionEvent event) {
        ruta.pasarRutasAdmin("AdminReservaciones" , but_Reservaciones);
    }

    // metodos para ir a formularios

    @FXML
    void but_agregar(ActionEvent event) {
        Estado_boton = "agregar";
        ruta.pasarRutasAdminFroms("formAddEmpleado", but_agregar);
    }

    @FXML
    void but_editar(ActionEvent event) {
        Map filaSeleccionada = (Map) TablaEmpleado.getSelectionModel().getSelectedItem();

        if (filaSeleccionada != null) {
            String Cliente = filaSeleccionada.get("nombre_empleado").toString() +" "+ filaSeleccionada.get("apellido_empleado").toString();
            Email_seleccionado = filaSeleccionada.get("email").toString();
            // Creamos una variable de tipo Optional que almacena un dato de tipo boton, esto nos servira para saber que opcion tomo el usuario
            Optional<ButtonType> resultado = alert.showConfirmationAlert("Editar Empleado", null, "¿Seguro que quieres editar el empleado: " + Cliente + "?");

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // Cambiar la vista al formulario de edición y le asignamos al boton el estado de editar
                Estado_boton = "editar";
                ruta.pasarRutasAdminFroms("formAddEmpleado",but_Cliente);
            }

        }else{
            alert.showInfoAlert("No se ha seleccionado ninguna fila",null,"Seleccione un empleado");
        }
    }

    @FXML
    void but_eliminar(ActionEvent event) {
        empleadoDAO empleadoDAO = new empleadoDAO();
        String nombre = "";
        String Email = "";
        Map<String,Object> filaSeleccionada = TablaEmpleado.getSelectionModel().getSelectedItem();

        // Si selecciono una fila
        if (filaSeleccionada != null) {
            Email = (String) filaSeleccionada.get("email");
            nombre = filaSeleccionada.get("nombre_empleado").toString() + " "+filaSeleccionada.get("apellido_empleado").toString();

            //tomar la seleccion del usuario
            Optional<ButtonType> opcion = alert.showConfirmationAlert("Eliminar Empleado",null,"Seguro quieres Eliminar este Empleado: " + nombre);

            //Si la Seleccion fue si
            if (opcion.isPresent() && opcion.get() == ButtonType.OK) {
                empleadoDAO.DELETE_EMPLEADO(Email);
                Filltable();
            }

        }else{//Si el boton se presiono y ninguna fila fue seleccionada
            alert.showInfoAlert("No se ha seleccionado ninguna fila",null,"Seleccione un Empleado");
        }
    }

    public void Filltable(){
        ObservableList<Map> lista = empleadoDAO.getEmpleados();

        TablaEmpleado.setItems(lista);

        ColumnNombre.setCellValueFactory(new MapValueFactory<>("nombre_empleado"));
        ColumnApellido.setCellValueFactory(new MapValueFactory<>("apellido_empleado"));
        ColumnTelefono.setCellValueFactory(new MapValueFactory<>("telefono_1"));
        ColumnDireccion.setCellValueFactory(new MapValueFactory<>("direccion"));
        ColumnDUI.setCellValueFactory(new MapValueFactory<>("DUI_empleado"));
        ColumnCorreo.setCellValueFactory(new MapValueFactory<>("email"));
        ColumnCargo.setCellValueFactory(new MapValueFactory<>("nombre_cargo"));
        ColumnEstado.setCellValueFactory(new MapValueFactory<>("estado_empleado"));

        ColumnEstado.setCellFactory(column -> new TableCell<Map, Object>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null); // Si la celda está vacía
                } else {
                    int estado = Integer.parseInt(item.toString());
                    setText(estado == 1 ? "Activo" : "Inactivo"); // Cambia el texto según el valor
                }
            }
        });
    }

    public void but_login(javafx.scene.input.MouseEvent mouseEvent) {
        ruta.pasarRutasLogin("Login", but_Cliente);
    }

}
