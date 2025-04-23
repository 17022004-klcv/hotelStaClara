package com.example.hotelstaclara.controllers.formsAdminControllers;

import com.example.hotelstaclara.Alert.Alert;
import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.controllers.AdminController.AdminHabitacionesController;
import com.example.hotelstaclara.database.HabiracionDAO;
import com.example.hotelstaclara.model.Estado_habitacion;
import com.example.hotelstaclara.model.habitacion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
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
        if (OpAddEdit.equals("add")) {
            addHabitacion();
        } else if (OpAddEdit.equals("edit")) {
            editarHabitacion(habitacion);
        }
    }

    private void editarHabitacion(habitacion h) throws SQLException {
        String numeroHabitacion = txt_numHabitaacion.getText();
        String tipo = comb_tipoHabitacion.getValue();
        int capacidad = Integer.parseInt(txt_capacidad.getText());
        double precio = Double.parseDouble(txt_monto.getText());
        Estado_habitacion estado = comb_estado.getValue();

        HabiracionDAO habitacionesDAO = new HabiracionDAO();
        habitacionesDAO.actualizarHabitacion(new habitacion(h.getId_habitacion(), numeroHabitacion, tipo, capacidad, precio, estado));
    }


    private void addHabitacion() throws SQLException {
        String numeroHabitacion = txt_numHabitaacion.getText();
        String tipo = comb_tipoHabitacion.getValue();
        int capacidad = Integer.parseInt(txt_capacidad.getText());
        double precio = Double.parseDouble(txt_monto.getText());
        Estado_habitacion estado = comb_estado.getValue();

        HabiracionDAO habitacionesDAO = new HabiracionDAO();
        habitacionesDAO.insertarHabitacion(new habitacion(0,numeroHabitacion, tipo, capacidad, precio, estado));
    }



    private void validarCampos() {

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

}

