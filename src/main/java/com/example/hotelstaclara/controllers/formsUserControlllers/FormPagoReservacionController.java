package com.example.hotelstaclara.controllers.formsUserControlllers;

import com.example.hotelstaclara.model.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FormPagoReservacionController {

    @FXML
    private Button bt_pagar;

    @FXML
    private ComboBox<?> cb_tipo_pago;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_cliente;

    @FXML
    private TextField txt_dui;

    @FXML
    private TextField txt_id_reservacion;

    @FXML
    private TextField txt_monto;

    Rutas ruta = new Rutas();

    @FXML
    void bt_pagar(ActionEvent event) {

    }

    @FXML
    void cb_tipo_pago(ActionEvent event) {

    }

    @FXML
    void imgBack(MouseEvent event) {
        ruta.cerrarVentana(bt_pagar);
    }

    @FXML
    void txt_cliente(ActionEvent event) {

    }

    @FXML
    void txt_dui(ActionEvent event) {

    }

    @FXML
    void txt_id_reservacion(ActionEvent event) {

    }

    @FXML
    void txt_monto(ActionEvent event) {

    }

}
