package com.example.hotelstaclara.controllers.AdminController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminHabitacionesController {

    @FXML
    private Button but_Clientes;

    @FXML
    private Button but_Empleados;

    @FXML
    private Button but_Pagos;

    @FXML
    private Button but_Reservaciones;

    @FXML
    void but_Clientes(ActionEvent event) {
    pasarVista("AdminClientes");
    }

    @FXML
    void but_Empleados(ActionEvent event) {
    pasarVista("AdminEmpleados");
    }

    @FXML
    void but_Pagos(ActionEvent event) {
    pasarVista("AdminPagos");
    }

    @FXML
    void but_Reservaciones(ActionEvent event) {
    pasarVista("AdminReservaciones");

    }

    private void pasarVista(String ruta) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelstaclara/views/Admin/"+ruta+".fxml"));
            Parent root = loader.load();

            // Cambiar la escena a la nueva vista
            Stage stage = (Stage) but_Pagos.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
