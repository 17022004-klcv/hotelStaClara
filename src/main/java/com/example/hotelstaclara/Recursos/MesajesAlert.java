package com.example.hotelstaclara.Recursos;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MesajesAlert {


    public  void mostarAlertError(String message) {
  
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public  void mostarAlertWARNING(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error de Login");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public  boolean mostrarConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText("Confirmación requerida");
        alert.setContentText(mensaje);

        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }

    public  void showInfoAlert(String title, String heading, String message) {
        showAlert(javafx.scene.control.Alert.AlertType.INFORMATION, title, heading, message);
    }
    public  boolean showWarningAlert(String title, String heading, String message) {
        showAlert(javafx.scene.control.Alert.AlertType.WARNING, title, heading, message);
        return false;
    }

    public  void showErrorAlert(String title, String heading, String message) {
        showAlert(javafx.scene.control.Alert.AlertType.ERROR, title, heading, message);
    }

    public Optional<ButtonType> showConfirmationAlert(String title, String heading, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(heading);
        alert.setContentText(message);
        return alert.showAndWait();
    }

    public void showAlert(javafx.scene.control.Alert.AlertType alertType, String title, String heading, String message)
    {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(heading);
        alert.setContentText(message);
        alert.showAndWait();

    }
}
