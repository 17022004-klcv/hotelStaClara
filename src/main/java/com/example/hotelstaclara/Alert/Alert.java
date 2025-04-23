package com.example.hotelstaclara.Alert;

import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alert {
    public static void showInfoAlert(String title, String heading, String message) {
        showAlert(javafx.scene.control.Alert.AlertType.INFORMATION, title, heading, message);
    }
    public static boolean showWarningAlert(String title, String heading, String message) {
        showAlert(javafx.scene.control.Alert.AlertType.WARNING, title, heading, message);
        return false;
    }

    public static void showErrorAlert(String title, String heading, String message) {
        showAlert(javafx.scene.control.Alert.AlertType.ERROR, title, heading, message);
    }

    public static Optional<ButtonType> showConfirmationAlert(String title, String heading, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(heading);
        alert.setContentText(message);
        return alert.showAndWait();
    }

    public static void showAlert(javafx.scene.control.Alert.AlertType alertType, String title, String heading, String message)
    {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(heading);
        alert.setContentText(message);
        alert.showAndWait();

    }
}
