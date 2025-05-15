package com.example.hotelstaclara.controllers.formsAdminControllers;

import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.controllers.AdminController.AdminEmpleadosController;
import com.example.hotelstaclara.validations.validaciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.SQLException;

public class formAddEmpleadoController {

    @FXML
    private Button bt_agregar;

    @FXML
    private RadioButton btnAdministrador;

    @FXML
    private RadioButton btnLimpieza;

    @FXML
    private RadioButton btnServicio;

    @FXML
    private ImageView imgBack;

    @FXML
    private Text lblTitular;

    @FXML
    private ToggleGroup rdoCargo;

    @FXML
    private TextField txtCelular;

    @FXML
    private java.awt.TextField txt_apellidos;

    @FXML
    private java.awt.TextField txt_direccion;

    @FXML
    private java.awt.TextField txt_dui;

    @FXML
    private java.awt.TextField txt_email;

    @FXML
    private java.awt.TextField txt_nombres;

    @FXML
    private java.awt.TextField txt_tel;

    Rutas ruta=new Rutas();
    private String nombre, apellido, dui, correo, telefono, direccion;
    private int cargo,estado, ultimo_contacto, ultimo_empleado;
    public static String CorreoEmpleado;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        String estado_boton = AdminEmpleadosController.Estado_boton;
        CorreoEmpleado = AdminEmpleadosController.Email_seleccionado;

        if(estado_boton.equals("agregar")){
            lblTitular.setText("Agregar Empleado");
            bt_agregar.setText("Agregar Empleado");
        }else{
            lblTitular.setText("Editar Empleado");
            bt_agregar.setText("Finalizar edicion");
            Rellenar();
        }
    }

    @FXML
    void bt_agregar(ActionEvent event) {
        String estado_boton = AdminEmpleadosController.Estado_boton;

        if(estado_boton.equals("agregar")){
            agregarEmpleado();
        }else{
            Editar_Empleado();
        }
    }

    private void agregarEmpleado() throws SQLException {
        // Llamar a las validaciones en la clase Validacion
        if (validaciones.validarNombreApellido(txt_nombres, txt_apellidos) &&
                validaciones.validarCorreo(txt_email) &&
                validaciones.validarTelefono(txt_tel) &&
                validaciones.validarDUI(txt_dui) &&
                validaciones.validarDireccion(txt_direccion) &&
                validaciones.validarCargoSeleccionado(rdoCargo)) {
            setear_empleado();
        }
    }

    public void setear_empleado() throws SQLException {
        //Creamos los respectivos objetos para cada insersion de datos, siguiento este orden, porque sino explota
        ContactoDAO insertar_contact = new ContactoDAO(); // Primero
        EmpleadoDAO insertar_Empleado = new EmpleadoDAO(); // Segundo
        EmailDAO insertar_email = new EmailDAO();       // Tercero

        Connection con = Conection.getConnection();
        //Colocamos el valor de los textfiel a variables para hacerlo mas legible
        nombre = txtNombre.getText();
        apellido = txtApellido.getText();
        dui = txtDUI.getText();
        correo = txtCorreo.getText();
        telefono = txtTelefono.getText();
        direccion = txtDireccion.getText();

        if(rdoCargo.getSelectedToggle() == btnAdministrativo){
            cargo = 1;
        }else if(rdoCargo.getSelectedToggle() == btnServicio){
            cargo = 2;
        }else{
            cargo = 3;
        }

        if(rdoEstado.getSelectedToggle() == btnInactivo){
            estado = 0;
        }else{
            estado = 1;
        }

        //Obtenemos el ultimo id registrado, segund el orden en que los ingresamos
        // Esto para que tengan congruencia que el contacto ingresado, se coloca en el insert de cliente, y para email, se necesita el id cliente
        // se le coloca +1 porque sino, estaria agarrando el ultimo registrado antes que nosotros

        try {
            con.setAutoCommit(false); // Inicia la transacción

            //Insertamos los datos en contacto
            Contacto contacto =new Contacto(telefono,direccion);
            insertar_contact.INSERT(contacto);
            ultimo_contacto = ContactoDAO.Obtener_ultimo_contacto();

            //Insertamos los datos en cliente
            Empleado empleado=new Empleado(nombre,apellido,dui, ultimo_contacto,cargo,estado);
            insertar_Empleado.INSERT_EMPLEADO(empleado);
            ultimo_empleado = EmailDAO.ObtenerUltimoid_empleado();

            //Insertamos el correo del cliente
            insertar_email.Insert_Email_Empleado(correo,ultimo_empleado);

            //Limpiamos las casillas
            Limpiar();
        } catch (SQLException e) {
            con.rollback(); // Revierte los cambios si ocurre un error
            throw new SQLException("Error al registrar el empleado", e);
        } finally {
            con.setAutoCommit(true); // Restaura el estado de la conexión
        }


    }

    @FXML
    void imgBack(MouseEvent event) {
        ruta.pasarRutasAdmin("AdminEmpleadosController", bt_agregar);
    }

    @FXML
    void txt_cel(MouseEvent event) {

    }

}
