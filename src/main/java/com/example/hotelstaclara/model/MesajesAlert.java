package com.example.hotelstaclara.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MesajesAlert {

    public void mostarAlertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Login");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void mostarAlertWARNING(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error de Login");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public static boolean mostrarConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText("Confirmación requerida");
        alert.setContentText(mensaje);

        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
}
