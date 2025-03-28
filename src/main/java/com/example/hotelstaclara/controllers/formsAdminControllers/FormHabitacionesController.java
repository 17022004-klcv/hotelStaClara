package com.example.hotelstaclara.controllers.formsAdminControllers;

import com.example.hotelstaclara.model.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FormHabitacionesController {

    @FXML
    private Button bt_agregar;

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
    void bt_agregar(ActionEvent event) {

    }

    @FXML
    void imgBack(MouseEvent event) {
        ruta.cerrarVentana(bt_agregar);
    }

}
