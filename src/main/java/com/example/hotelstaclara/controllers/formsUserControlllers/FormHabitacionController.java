package com.example.hotelstaclara.controllers.formsUserControlllers;

import com.example.hotelstaclara.model.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FormHabitacionController {

    @FXML
    private Button bt_actualizar;

    @FXML
    private ComboBox<?> comb_estado;

    @FXML
    private ComboBox<?> comb_tipoHabitacion;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_capacidad;

    @FXML
    private TextField txt_monto;

    @FXML
    private TextField txt_numHabitaacion;

    Rutas ruta = new Rutas();
    @FXML
    void bt_actualizar(ActionEvent event) {

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

}
