package com.example.hotelstaclara.Recursos;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button; // Importa Button correctamente

import javax.swing.*;
import java.io.IOException;

public class Rutas {


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



    //  metodos para pasar visra en la parte de recepcionista froms
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

            nuevoStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  metodos para pasar vista en la parte de login

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
