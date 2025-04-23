package com.example.hotelstaclara.controllers.formsAdminControllers;

import com.example.hotelstaclara.Recursos.Rutas;
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

    Rutas ruta = new Rutas();
    @FXML
    public void initialize() {
        // Llenar combo de estado
        comb_estado.setItems(FXCollections.observableArrayList(Estado_habitacion.values()));

        // Llenar combo de tipo de habitación
        comb_tipoHabitacion.setItems(FXCollections.observableArrayList(
                "Sencilla", "Doble", "Matrimonial"
        ));
    }

    @FXML
    void bt_agregar(ActionEvent event) throws SQLException {
        String numeroHabitacion = txt_numHabitaacion.getText();
        String tipo = comb_tipoHabitacion.getValue();
        int capacidad = Integer.parseInt(txt_capacidad.getText());
        double precio = Double.parseDouble(txt_monto.getText());
        Estado_habitacion estado = comb_estado.getValue();

        HabiracionDAO habitacionesDAO = new HabiracionDAO();
        habitacionesDAO.insertarHabitacion(new habitacion(numeroHabitacion, tipo, capacidad, precio, estado));
    }

    @FXML
    void imgBack(MouseEvent event) {
        ruta.cerrarVentana(bt_agregar);
    }

}

