//package com.example.hotelstaclara.controllers.formsUserControlllers;
package com.example.hotelstaclara.controllers.formsUserControlllers;
import com.example.hotelstaclara.Recursos.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FormAddClienteController {
    @FXML
    private Button bt_agregar;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_apellidos;

    @FXML
    private TextField txt_cel;

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

    Rutas ruta  = new Rutas();

    @FXML
    void br_agregar(ActionEvent event) {

    }

    @FXML
    void imgBack(MouseEvent event) {
        ruta.cerrarVentana(bt_agregar);
    }

    @FXML
    void txt_apellidos(ActionEvent event) {

    }

    @FXML
    void txt_cel(ActionEvent event) {

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