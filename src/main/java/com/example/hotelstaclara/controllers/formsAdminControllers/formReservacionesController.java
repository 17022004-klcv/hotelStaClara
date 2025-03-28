package com.example.hotelstaclara.controllers.formsAdminControllers;

import com.example.hotelstaclara.model.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class formReservacionesController {

    @FXML
    private Button but_Aceptar;

    @FXML
    private ImageView imgBack;

    @FXML
    private DatePicker pick_fechaInicio;

    @FXML
    private DatePicker pick_fechaSalida;

    @FXML
    private TextField txt_cliente;

    @FXML
    private TextField txt_habitacion;

    Rutas ruta = new Rutas();

    @FXML
    void but_Aceptar(ActionEvent event) {

    }

    @FXML
    void imgBack(MouseEvent event) {
        ruta.cerrarVentana(but_Aceptar);
    }

    @FXML
    void txt_cliente(ActionEvent event) {

    }

    @FXML
    void txt_habitacion(ActionEvent event) {

    }

}
