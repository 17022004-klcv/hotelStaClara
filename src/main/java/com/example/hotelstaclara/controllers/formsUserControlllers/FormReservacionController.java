package com.example.hotelstaclara.controllers.formsUserControlllers;

import com.example.hotelstaclara.model.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FormReservacionController {

    @FXML
    private Button but_Aceptar;

    @FXML
    private ComboBox<?> cb_cliente;

    @FXML
    private ComboBox<?> cb_habitacion;

    @FXML
    private ImageView imgBack;

    @FXML
    private DatePicker pick_fechaInicio;

    @FXML
    private DatePicker pick_fechaSalida;

    @FXML
    private RadioButton rb_estandar;

    @FXML
    private RadioButton rb_premium;

    @FXML
    private RadioButton rb_royal;

    Rutas ruta = new Rutas();

    @FXML
    void but_Aceptar(ActionEvent event) {

    }

    @FXML
    void cb_cliente(ActionEvent event) {

    }

    @FXML
    void cb_habitacion(ActionEvent event) {

    }

    @FXML
    void imgBack(MouseEvent event) {
        ruta.cerrarVentana(but_Aceptar);
    }

    @FXML
    void rb_estandar(ActionEvent event) {

    }

    @FXML
    void rb_premium(ActionEvent event) {

    }

    @FXML
    void rb_royal(ActionEvent event) {

    }

}
