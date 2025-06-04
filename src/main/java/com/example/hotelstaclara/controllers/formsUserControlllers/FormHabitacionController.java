package com.example.hotelstaclara.controllers.formsUserControlllers;

import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.controllers.UserControllers.USERhabitaciones;
import com.example.hotelstaclara.database.HabiracionDAO;
import com.example.hotelstaclara.model.Estado_habitacion;
import com.example.hotelstaclara.model.habitacion;
import com.example.hotelstaclara.validations.validaciones;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class FormHabitacionController {

    @FXML
    private Button bt_actualizar;

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

    private USERhabitaciones userhabitaciones;

    private habitacion habitacion;

    Rutas ruta = new Rutas();

    public void initialize() {
    }


    public void inicializarFormulario() {
        comb_estado.setItems(FXCollections.observableArrayList(Estado_habitacion.values()));
        comb_tipoHabitacion.setItems(FXCollections.observableArrayList("Sencilla", "Doble", "Matrimonial"));

        comb_tipoHabitacion.getSelectionModel().selectFirst();
        comb_estado.getSelectionModel().selectFirst();

            txt_numHabitaacion.setText(habitacion.getNumero_habitacion());
            comb_tipoHabitacion.setValue(habitacion.getTipo_habitacion());
            txt_capacidad.setText(String.valueOf(habitacion.getCapacidad()));
            txt_monto.setText(String.valueOf(habitacion.getPrecio()));
            comb_estado.setValue(habitacion.getEstado_habitacion());

    }

    @FXML
    void bt_actualizar(ActionEvent event) throws SQLException {

        if (!validarCamposFormulario()) {
            return;
        }

        String numeroHabitacion = txt_numHabitaacion.getText();
        String tipo = comb_tipoHabitacion.getValue();
        int capacidad = Integer.parseInt(txt_capacidad.getText());
        double precio = Double.parseDouble(txt_monto.getText());
        Estado_habitacion estado = comb_estado.getValue();

        HabiracionDAO habitacionesDAO = new HabiracionDAO();
        habitacionesDAO.actualizarHabitacion(new habitacion(habitacion.getId_habitacion(), numeroHabitacion, tipo, capacidad, precio, estado));
        ruta.cerrarVentana(bt_actualizar);
    }

    private boolean validarCamposFormulario() {
        return  validaciones.validarNumeroHabitacion(txt_numHabitaacion)
                && validaciones.validarCombo(comb_tipoHabitacion, "Tipo de Habitación")
                && validaciones.validarCapacidad(txt_capacidad)
                && validaciones.validarPrecio(txt_monto)
                && validaciones.validarCombo(comb_estado, "Estado");
    }

    @FXML
    void comb_estado(ActionEvent event) {

    }

    @FXML
    void comb_tipoHabitacion(ActionEvent event) {

    }

    @FXML
    void imgBack(MouseEvent event) {
    ruta.cerrarVentana(bt_actualizar);
    }

    @FXML
    void txt_monto(ActionEvent event) {
    }

    public void setHabitacion(com.example.hotelstaclara.model.habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setUserhabitaciones(USERhabitaciones userhabitaciones) {
        this.userhabitaciones = userhabitaciones;
    }




}
