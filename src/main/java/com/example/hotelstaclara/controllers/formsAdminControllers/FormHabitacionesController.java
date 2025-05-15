package com.example.hotelstaclara.controllers.formsAdminControllers;

import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.controllers.AdminController.AdminHabitacionesController;
import com.example.hotelstaclara.database.HabiracionDAO;
import com.example.hotelstaclara.model.Estado_habitacion;
import com.example.hotelstaclara.model.habitacion;
import com.example.hotelstaclara.validations.validaciones;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class FormHabitacionesController {

    @FXML
    private Button bt_agregar;

    @FXML
    private ComboBox<Estado_habitacion> comb_estado;

    @FXML
    private ComboBox<String> comb_tipoHabitacion;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_capacidad;

    @FXML
    private TextField txt_monto;

    @FXML
    private TextField txt_numHabitaacion;

    private String OpAddEdit;
    habitacion habitacion;

    Rutas ruta = new Rutas();
    @FXML
    public void initialize() {
    }

    public void inicializarFormulario() {
        comb_estado.setItems(FXCollections.observableArrayList(Estado_habitacion.values()));
        comb_tipoHabitacion.setItems(FXCollections.observableArrayList("Sencilla", "Doble", "Matrimonial"));

        comb_tipoHabitacion.getSelectionModel().selectFirst();
        comb_estado.getSelectionModel().selectFirst();

        if (OpAddEdit.equals("edit") && habitacion != null) {
            txt_numHabitaacion.setText(habitacion.getNumero_habitacion());
            comb_tipoHabitacion.setValue(habitacion.getTipo_habitacion());
            txt_capacidad.setText(String.valueOf(habitacion.getCapacidad()));
            txt_monto.setText(String.valueOf(habitacion.getPrecio()));
            comb_estado.setValue(habitacion.getEstado_habitacion());
        }
    }


    @FXML
    void bt_agregar(ActionEvent event) throws SQLException {

        if (!validarCamposFormulario()) {
            return;
        }

        if (OpAddEdit.equals("add")) {
            addHabitacion();
        } else if (OpAddEdit.equals("edit")) {
            editarHabitacion();
            ruta.cerrarVentana(bt_agregar);
        }
    }


    private void addHabitacion() throws SQLException {
        HabiracionDAO habitacionesDAO = new HabiracionDAO();
        habitacionesDAO.insertarHabitacion(obtenerDatosFormulario());
    }

    private void editarHabitacion() throws SQLException {
        HabiracionDAO habitacionesDAO = new HabiracionDAO();
        habitacionesDAO.actualizarHabitacion(obtenerDatosFormulario());
    }


    private habitacion obtenerDatosFormulario() {
        String numeroHabitacion = txt_numHabitaacion.getText();
        String tipo = comb_tipoHabitacion.getValue();
        int capacidad = Integer.parseInt(txt_capacidad.getText());
        double precio = Double.parseDouble(txt_monto.getText());
        Estado_habitacion estado = comb_estado.getValue();

        int id = (OpAddEdit.equals("edit") && habitacion != null) ? habitacion.getId_habitacion() : 0;

        return new habitacion(id, numeroHabitacion, tipo, capacidad, precio, estado);
    }

    private boolean validarCamposFormulario() {
        return  validaciones.validarNumeroHabitacion(txt_numHabitaacion)
                && validaciones.validarCombo(comb_tipoHabitacion, "Tipo de Habitación")
                && validaciones.validarCapacidad(txt_capacidad)
                && validaciones.validarPrecio(txt_monto)
                && validaciones.validarCombo(comb_estado, "Estado");
    }


    @FXML
    void imgBack(MouseEvent event) {
        ruta.cerrarVentana(bt_agregar);
    }

    public void setOpAddEdit(String opAddEdit) {
        OpAddEdit = opAddEdit;
    }

    public void setHabitacion(com.example.hotelstaclara.model.habitacion habitacion) {
        this.habitacion = habitacion;
    }


    private AdminHabitacionesController adminController;

    public void setAdminController(AdminHabitacionesController controller) {
        this.adminController = controller;
    }

    public AdminHabitacionesController getAdminController() {
        return adminController;
    }


}

