package com.example.hotelstaclara.controllers.formsAdminControllers;

import com.example.hotelstaclara.Recursos.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Map;

public class formAddEmpleadoController {

    @FXML
    private Button bt_agregar;

    @FXML
    private ComboBox<String> cb_cargo;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txtCelular;

    @FXML
    private TextField txt_apellidos;

    @FXML
    private TextField txt_direccion;

    @FXML
    private TextField txt_dui;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_nombres;

    @FXML
    private TextField txt_tel;

    Rutas ruta = new Rutas();


    @FXML
    void bt_agregar(ActionEvent event) {

    }

    @FXML
    void cb_cargo(ActionEvent event) {

    }

    @FXML
    void imgBack(MouseEvent event) {
        ruta.cerrarVentana(bt_agregar);
    }

    @FXML
    void txtCelular(ActionEvent event) {

    }

    @FXML
    void txt_apellidos(ActionEvent event) {

    }

    @FXML
    void txt_cel(MouseEvent event) {

    }

    @FXML
    void txt_direccion(ActionEvent event) {

    }

    @FXML
    void txt_dui(ActionEvent event) {

    }

    @FXML
    void txt_email(ActionEvent event) {

    }

    @FXML
    void txt_nombres(ActionEvent event) {

    }

    @FXML
    void txt_tel(ActionEvent event) {

    }

}
