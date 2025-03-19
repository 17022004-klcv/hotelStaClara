package com.example.hotelstaclara.controllers;
import com.example.hotelstaclara.database.loginDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class loginController {

    @FXML
    private Button bt_entrar;

    @FXML
    private TextField txt_contra;

    @FXML
    private TextField txt_usuario;

    @FXML
    void bt_entrar(ActionEvent event) {

        String nombreUsuario = txt_usuario.getText();
        String contrasena = txt_contra.getText();
        loginDAO logindao=new loginDAO();

        boolean credencialesValidas = logindao.verificarCredenciales(nombreUsuario, contrasena);

        if (credencialesValidas) {
            JOptionPane.showMessageDialog(null,"!Bienvenido " + nombreUsuario);
        } else {
            System.out.println("Error en el inicio de sesion");
        }
    }

    @FXML
    void txt_contra(ActionEvent event) {

    }

    @FXML
    void txt_usuario(ActionEvent event) {

    }

    public void CambiarVista(String Direccion){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/"+Direccion+".fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) bt_entrar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

