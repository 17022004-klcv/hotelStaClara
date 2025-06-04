package com.example.hotelstaclara.controllers.formsAdminControllers;

import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.database.clienteDAO;
import com.example.hotelstaclara.model.cliente;
import com.example.hotelstaclara.model.contacto;
import com.example.hotelstaclara.model.email;
import com.example.hotelstaclara.validations.validaciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.*;

public class formAddClienteController {

    @FXML
    private Button bt_agregar;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txt_apellidos;

    @FXML
    private TextField txt_cel;

    @FXML
    private TextField txt_direccion;

    @FXML
    private TextField txt_dui;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_nombres;

    @FXML
    private TextField txt_tel;

    Rutas ruta = new Rutas();

    @FXML
    void br_agregar(ActionEvent event) {
        if (!validar_campos()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos correctamente");
            return;
        }

        contacto contacCliente = new contacto();
        cliente client = new cliente();
        email mail = new email();

        // Seteo de valores (esto está bien)
        contacCliente.setTelefono_1(txt_tel.getText());
        contacCliente.setTelefono_2(txt_cel.getText());
        contacCliente.setDireccion(txt_direccion.getText());

        client.setNombre_cliente(txt_nombres.getText());
        client.setApellido_cliente(txt_apellidos.getText());
        client.setDUI_cliente(txt_dui.getText());
        client.setEstado_cliente(1);

        mail.setEmail(txt_email.getText());
        mail.setId_empleado(1);

        clienteDAO cliente = new clienteDAO();

        try {
            if (bt_agregar.getText().equals("Editar")) {
                cliente.actualizarCliente(contacCliente, client, mail);
            } else {
                cliente.insertarCliente(contacCliente, client, mail);
            }

            limpiar_campos();
            ruta.cerrarVentana(bt_agregar);

            if (onSuccessCallback != null) {
                onSuccessCallback.run();
            }

        } catch (Exception e) {
            e.printStackTrace(); // Para depuración
        }
    }

    public boolean validar_campos() {
        // Validación plana sin anidamiento excesivo
        boolean valido = true;

        if (!validaciones.validarNombreApellido(txt_nombres, txt_apellidos)) {
            valido = false;
        }
        if (!validaciones.validarDUI(txt_dui)) {
            valido = false;
        }
        if (!validaciones.validarCorreo(txt_email)) {
            valido = false;
        }
        if (!validaciones.validarTelefono(txt_tel)) {
            valido = false;
        }
        if (!validaciones.validarTelefono(txt_cel)) {
            valido = false;
        }
        if (!validaciones.validarDireccion(txt_direccion)) {
            valido = false;
        }

        return valido;
    }
    public void limpiar_campos() {
        txt_tel.setText("");
        txt_cel.setText("");
        txt_direccion.setText("");
        txt_nombres.setText("");
        txt_apellidos.setText("");
        txt_dui.setText("");
        txt_email.setText("");
    }


    public void setDatos(String nombre, String apellido, String telefono, String celular, String direccion, String dui, String email) {
        txt_nombres.setText(nombre);
        txt_apellidos.setText(apellido);
        txt_tel.setText(telefono);
        txt_cel.setText(celular);
        txt_direccion.setText(direccion);
        txt_dui.setText(dui);
        txt_email.setText(email);
    }

    public void setButton(String boton){
        bt_agregar.setText(boton);
    }

    // En formAddClienteController.java
    private Runnable onSuccessCallback;

    public void setOnSuccessCallback(Runnable callback) {
        this.onSuccessCallback = callback;
    }

    @FXML
    void imgBack(MouseEvent event) {
    ruta.cerrarVentana(bt_agregar);
    }

    @FXML
    void txt_apellidos(ActionEvent event) {
    }

    @FXML
    void txt_cel(ActionEvent event) {

    }

    @FXML
    void txt_direccion(ActionEvent event) {

    }

    @FXML
    void txt_dui(ActionEvent event) {

    }

    @FXML
    void txt_email(ActionEvent event) {

    }

    @FXML
    void txt_nombres(ActionEvent event) {

    }

    @FXML
    void txt_tel(ActionEvent event) {

    }

}

