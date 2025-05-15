package com.example.hotelstaclara.controllers.formsAdminControllers;

import com.example.hotelstaclara.Alert.Alert;
import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.controllers.AdminController.AdminEmpleadosController;
import com.example.hotelstaclara.database.ContactoDAO;
import com.example.hotelstaclara.database.connection;
import com.example.hotelstaclara.database.emailDAO;
import com.example.hotelstaclara.database.empleadoDAO;
import com.example.hotelstaclara.model.contacto;
import com.example.hotelstaclara.model.empleado;
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

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class formAddEmpleadoController {

    @FXML
    private Button bt_agregar;

    @FXML
    private RadioButton btnActivo;

    @FXML
    private RadioButton btnAdministrador;

    @FXML
    private RadioButton btnInActivo;

    @FXML
    private RadioButton btnRecepcionista;

    @FXML
    private ImageView imgBack;

    @FXML
    private Text lblTitular;

    @FXML
    private ToggleGroup rdoCargo;

    @FXML
    private ToggleGroup rdoEstado;

    @FXML
    private TextField txt_apellidos;

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


    MesajesAlert alert=new MesajesAlert();
    Rutas ruta=new Rutas();
    private String nombre, apellido, dui, correo, telefono, direccion;
    private int cargo,estado, ultimo_contacto, ultimo_empleado;
    public String CorreoEmpleado;

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
    void bt_agregar(ActionEvent event) throws SQLException {
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
        empleadoDAO insertar_Empleado = new empleadoDAO(); // Segundo
        emailDAO insertar_email = new emailDAO();       // Tercero

        Connection con = connection.getConnection();
        //Colocamos el valor de los textfiel a variables para hacerlo mas legible
        nombre = txt_nombres.getText();
        apellido = txt_apellidos.getText();
        dui = txt_dui.getText();
        correo = txt_email.getText();
        telefono = txt_tel.getText();
        direccion = txt_direccion.getText();

        if(rdoCargo.getSelectedToggle() == btnAdministrador){
            cargo = 1;
        }else if(rdoCargo.getSelectedToggle() == btnRecepcionista) {
            cargo = 2;
        }

        if(rdoEstado.getSelectedToggle() == btnInActivo){
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
            contacto contacto =new contacto(telefono,direccion);
            insertar_contact.INSERT(contacto);
            ultimo_contacto = insertar_contact.Obtener_ultimo_contacto();

            //Insertamos los datos en cliente
            empleado empleado=new empleado(nombre,apellido,dui, ultimo_contacto,cargo,estado);
            insertar_Empleado.insertarEmpleado(empleado);
            ultimo_empleado = emailDAO.ObtenerUltimoid_empleado();

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

        AdminEmpleadosController adminempleado= new AdminEmpleadosController();

    }

    //Metodo para rellenar los campos segun la informacionde la base de datos, guiandonos por el correo el cual es unico para cada usuario
    public void Rellenar(){
        empleadoDAO empleadoDAO=new empleadoDAO();


        String email_Empleado = AdminEmpleadosController.Email_seleccionado;
        Map<String, Object> datosCliente = empleadoDAO.get_datos_Empleado(email_Empleado).get(0);

        // Cargar los datos en los TextFields del formulario
        txt_nombres.setText(datosCliente.get("nombre_empleado").toString());
        txt_apellidos.setText(datosCliente.get("apellido_empleado").toString());
        txt_dui.setText(datosCliente.get("DUI_empleado").toString());
        txt_email.setText(datosCliente.get("email").toString());
        txt_tel.setText(datosCliente.get("telefono_1").toString());
        txt_direccion.setText(datosCliente.get("direccion").toString());

    }

    // Metodo para realizar el update al cliente
    public void Editar_Empleado() throws SQLException {
        nombre = txt_nombres.getText();
        apellido = txt_apellidos.getText();
        dui = txt_dui.getText();
        correo = txt_email.getText();
        telefono = txt_tel.getText();
        direccion = txt_direccion.getText();

        if(rdoCargo.getSelectedToggle() == btnAdministrador){
            cargo = 1;
        }else if(rdoCargo.getSelectedToggle() == btnRecepcionista){
            cargo = 2;

        if(rdoEstado.getSelectedToggle() == btnInActivo){
            estado = 0;
        }else{
            estado = 1;
        }

        Map ids = empleadoDAO.Obtener_idsCorrespondientes_Empleados(CorreoEmpleado).get(0);
        // se coloca -1 por el hecho de que le estamos pasando el objeto completo, entonces este campo no puede ir vacio
        empleado empleado=new empleado(nombre,apellido,dui,-1,cargo,estado);

        //obtenemos el id de el usuario
        int id_contacto = (int) ids.get("id_contacto");
        int id_empleado = (int) ids.get("id_empleado");
        int id_email = (int) ids.get("id_email");

        //Creamos nuestros objetos
        ContactoDAO contactodao = new ContactoDAO();
        empleadoDAO empleadodao = new empleadoDAO();
        emailDAO emaildao = new emailDAO();

        //Actualizamos nuestro cliente
        try{
            contactodao.UPDATE_CONTACTO(telefono,direccion,id_contacto);
            empleadodao.UPDATE_Empleado(empleado, id_empleado);
            emaildao.UPDATE_Email(correo,id_email);


            alert.showInfoAlert("Exito",null,"El contacto se Actualizo correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Ocurrio un error al actualizar el contacto: " + e.getMessage(),"VUELVE A INTENTARLO",JOptionPane.ERROR_MESSAGE);
        }
}
    }

    //Limpia las casillas de todos los textfield
    public void Limpiar(){
        txt_nombres.setText("");
        txt_apellidos.setText("");
        txt_dui.setText("");
        txt_email.setText("");
        txt_tel.setText("");
        txt_direccion.setText("");
    }

    public void imgBack(MouseEvent mouseEvent) {
    }

    public void txt_cel(MouseEvent mouseEvent) {
    }
}
