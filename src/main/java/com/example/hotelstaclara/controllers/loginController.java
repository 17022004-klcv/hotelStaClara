package com.example.hotelstaclara.controllers;
import com.example.hotelstaclara.database.loginDAO;
import com.example.hotelstaclara.model.MesajesAlert;
import com.example.hotelstaclara.model.Rutas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.io.IOException;

public class loginController {

    @FXML
    private Button bt_entrar;

    @FXML
    private PasswordField txt_contra;

    @FXML
    private TextField txt_usuario;

    @FXML
    void bt_entrar(ActionEvent event) {
        String nombreUsuario = txt_usuario.getText();
        String contrasena = String.valueOf(txt_contra.getText());
        MesajesAlert mesajesAlert = new MesajesAlert();
        loginDAO logindao=new loginDAO();
        Rutas ruta =new Rutas();


        String credencialesValidas = logindao.verificarCredenciales(nombreUsuario, contrasena);
        if (credencialesValidas != null) {
            switch (credencialesValidas){
                case "administrador" -> ruta.pasarRutasAdmin("AdminReservaciones",bt_entrar);
                case "recepcionista" -> ruta.pasarRutasRecepcionista("USERpagos",bt_entrar);
                default -> mesajesAlert.mostarAlertWARNING("!El cargo no existe! " + nombreUsuario);
            }
        } else {
            mesajesAlert.mostarAlertError("Error en el inicio de sesion");
        }
    }

    @FXML
    void txt_contra(ActionEvent event) {
    }

    @FXML
    void txt_usuario(ActionEvent event) {

    }
}

