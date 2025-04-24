package com.example.hotelstaclara.Recursos;

import com.example.hotelstaclara.controllers.AdminController.AdminHabitacionesController;
import com.example.hotelstaclara.controllers.formsAdminControllers.FormHabitacionesController;
import com.example.hotelstaclara.model.habitacion;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button; // Importa Button correctamente

import javax.swing.*;
import java.io.IOException;

public class Rutas {

    private String opAddEdit = "";
    private habitacion habitation;
    private AdminHabitacionesController adminHabitacionesControllerOriginal;

    public Rutas() {
    }

    public void pasarRutasAdmin(String url, Button activador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelstaclara/views/Admin/" + url + ".fxml"));
            Parent root = loader.load();

            // Obtener la ventana actual desde el botón activador
            Stage stage = (Stage) activador.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pasarRutasAdminFroms(String url, Button activador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelstaclara/views/formsAdminViews/" + url + ".fxml"));
            Parent root = loader.load();

            //pasar variables/datos de adminHabitaciones a formHabitaciones
            FormHabitacionesController formHabitacionesController = loader.getController();
            formHabitacionesController.setOpAddEdit(opAddEdit);
            formHabitacionesController.setHabitacion(habitation);
            formHabitacionesController.inicializarFormulario();

            formHabitacionesController.setAdminController(adminHabitacionesControllerOriginal);

            // Crear un nuevo escenario (Stage) para la nueva ventana
            Stage nuevoStage = new Stage();
            Scene scene = new Scene(root);
            nuevoStage.setScene(scene);

            // Obtener la ventana actual desde el botón activador
            Stage stageActual = (Stage) activador.getScene().getWindow();

            // Configurar el nuevo Stage para que aparezca sobre el actual
            nuevoStage.initOwner(stageActual);
            nuevoStage.initModality(Modality.WINDOW_MODAL);

            formHabitacionesController.setAdminController(adminHabitacionesControllerOriginal);
            nuevoStage.setOnHidden(e -> adminHabitacionesControllerOriginal.llenarTablaHabitacion());


            // Mostrar el nuevo formulario
            nuevoStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrarVentana(Button btnCerrar) {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

//  metodos para pasar visra en la parte de recepcionista
    public void pasarRutasRecepcionista (String url, Button activador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelstaclara/views/User/" + url + ".fxml"));
            Parent root = loader.load();

            // Obtener la ventana actual desde el botón activador
            Stage stage = (Stage) activador.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pasarRutasRecepcionistaFroms(String url, Button activador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelstaclara/views/formsUserViews/" + url + ".fxml"));
            Parent root = loader.load();

            // Crear un nuevo escenario (Stage) para la nueva ventana
            Stage nuevoStage = new Stage();
            Scene scene = new Scene(root);
            nuevoStage.setScene(scene);

            // Obtener la ventana actual desde el botón activador
            Stage stageActual = (Stage) activador.getScene().getWindow();

            // Configurar el nuevo Stage para que aparezca sobre el actual
            nuevoStage.initOwner(stageActual);
            nuevoStage.initModality(Modality.WINDOW_MODAL);

            // Mostrar el nuevo formulario
            nuevoStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pasarRutasLogin(String url, Button activador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelstaclara/views/"+ url +".fxml"));
            Parent root = loader.load();

            // Obtener la ventana actual desde el botón activador
            Stage stage = (Stage) activador.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOpAddEdit(String opAddEdit) {
        this.opAddEdit = opAddEdit;
    }

    public void setHabitacion(habitacion habitacion) {
        this.habitation = habitacion;
    }

    public void setAdminController(AdminHabitacionesController adminController) {
        this.adminHabitacionesControllerOriginal = adminController;
    }
}

