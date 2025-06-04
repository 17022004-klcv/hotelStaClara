package com.example.hotelstaclara.controllers.formsAdminControllers;

import com.example.hotelstaclara.Alert.Alert;
import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.controllers.AdminController.AdminEmpleadosController;
import com.example.hotelstaclara.database.*;
import com.example.hotelstaclara.model.contacto;
import com.example.hotelstaclara.model.empleado;
import com.example.hotelstaclara.validations.validaciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private PasswordField txtContra;

    @FXML
    private TextField txtUsuaio;

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
            txt_dui.setEditable(false);
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
                validaciones.validarCargoSeleccionado(rdoCargo)&&
                validaciones.credenciales(txtUsuaio, txtContra)){

            setear_empleado();
        }
    }

    public void setear_empleado() throws SQLException {
        ContactoDAO insertar_contact = new ContactoDAO(); // 1
        empleadoDAO insertar_Empleado = new empleadoDAO(); // 2
        emailDAO insertar_email = new emailDAO(); // 3
        loginDAO insertarcredencial = new loginDAO(); // 4

        Connection con = connection.getConnection();

        // Captura datos desde la interfaz
        nombre = txt_nombres.getText();
        apellido = txt_apellidos.getText();
        dui = txt_dui.getText();
        correo = txt_email.getText();
        telefono = txt_tel.getText();
        direccion = txt_direccion.getText();

        // Cargos
        if (rdoCargo.getSelectedToggle() == btnAdministrador) {
            cargo = 1;
        } else if (rdoCargo.getSelectedToggle() == btnRecepcionista) {
            cargo = 2;
        }

        // Estado
        estado = (rdoEstado.getSelectedToggle() == btnInActivo) ? 0 : 1;

        try {
            con.setAutoCommit(false); // Inicia la transacción

            // 1. Insertar contacto
            contacto contacto = new contacto(telefono, direccion);
            insertar_contact.INSERT(contacto);
            int idContacto = insertar_contact.Obtener_ultimo_contacto();

            // 2. Insertar empleado
            empleado empleado = new empleado(nombre, apellido, dui, idContacto, cargo, estado);
            insertar_Empleado.insertarEmpleado(empleado);
            int idEmpleado = emailDAO.ObtenerUltimoid_empleado();

            // 3. Insertar email
            insertar_email.Insert_Email_Empleado(correo, idEmpleado);

            // 4. Insertar login
            insertarcredencial.insertarCredenciales(idEmpleado, txtUsuaio.getText(), txtContra.getText());

            con.commit();
            Limpiar();

        } catch (SQLException e) {
            con.rollback(); //revierte los cambios si algo falla
            alert.showErrorAlert("Error", null, "Error al registrar el empleado: " + e.getMessage());
            throw new SQLException("Error al registrar el empleado", e);

        } finally {
            con.setAutoCommit(true); // 🔓 Restaurar estado
        }
    }


    //Metodo para rellenar los campos segun la informacionde la base de datos, guiandonos por el correo el cual es unico para cada usuario
    public void Rellenar(){
        empleadoDAO empleadoDAO=new empleadoDAO();


        String email_Empleado = AdminEmpleadosController.Email_seleccionado;
        Map<String, Object> datosEmpleado = empleadoDAO.get_datos_Empleado(email_Empleado).get(0);

        // Cargar los datos en los TextFields del formulario
        txt_nombres.setText(datosEmpleado.get("nombre_empleado").toString());
        txt_apellidos.setText(datosEmpleado.get("apellido_empleado").toString());
        txt_dui.setText(datosEmpleado.get("DUI_empleado").toString());
        txt_email.setText(datosEmpleado.get("email").toString());
        txt_tel.setText(datosEmpleado.get("telefono_1").toString());
        txt_direccion.setText(datosEmpleado.get("direccion").toString());
        txtUsuaio.setText(datosEmpleado.get("usuario").toString());
        txtContra.setText(datosEmpleado.get("contraseña").toString());

        // Seleccionar el RadioButton de cargo según id_cargo
        int idCargo = Integer.parseInt(datosEmpleado.get("id_cargo").toString());
        if (idCargo == 1) {
            btnAdministrador.setSelected(true);
        } else if (idCargo == 2) {
            btnRecepcionista.setSelected(true);
        }

        // Seleccionar el RadioButton de estado según estado_empleado
        int estadoEmpleado = Integer.parseInt(datosEmpleado.get("estado_empleado").toString());
        if (estadoEmpleado == 0) {
            btnInActivo.setSelected(true);
        } else {
            btnActivo.setSelected(true);
        }

    }

    // Metodo para realizar el update al cliente
    public void Editar_Empleado() throws SQLException {
        nombre = txt_nombres.getText();
        apellido = txt_apellidos.getText();
        dui = txt_dui.getText();
        correo = txt_email.getText();
        telefono = txt_tel.getText();
        direccion = txt_direccion.getText();

        if (rdoCargo.getSelectedToggle() == btnAdministrador) {
            cargo = 1;
        } else if (rdoCargo.getSelectedToggle() == btnRecepcionista) {
            cargo = 2;
        }

        if (rdoEstado.getSelectedToggle() == btnInActivo) {
            estado = 0;
        } else {
            estado = 1;
        }

        Map ids = empleadoDAO.Obtener_idsCorrespondientes_Empleados(CorreoEmpleado).get(0);
        // se coloca -1 por el hecho de que le estamos pasando el objeto completo, entonces este campo no puede ir vacio
        empleado empleado = new empleado(nombre, apellido, dui, -1, cargo, estado);

        //obtenemos el id de el usuario
        int id_contacto = (int) ids.get("id_contacto");
        int id_empleado = (int) ids.get("id_empleado");
        int id_email = (int) ids.get("id_email");

        //Creamos nuestros objetos
        ContactoDAO contactodao = new ContactoDAO();
        empleadoDAO empleadodao = new empleadoDAO();
        emailDAO emaildao = new emailDAO();
        loginDAO logindao=new loginDAO();

        //Actualizamos nuestro cliente
        try {
            contactodao.UPDATE_CONTACTO(telefono, direccion, id_contacto);
            empleadodao.UPDATE_Empleado(empleado, id_empleado);
            emaildao.UPDATE_Email(correo, id_email);
            logindao.actualizarCredenciales(id_empleado,txtUsuaio.toString(),txtContra.toString());

            alert.showInfoAlert("Exito", null, "El contacto se Actualizo correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al actualizar el contacto: " + e.getMessage(), "VUELVE A INTENTARLO", JOptionPane.ERROR_MESSAGE);
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
        ruta.pasarRutasAdmin("AdminEmpleados",bt_agregar);
    }

    public void txt_cel(MouseEvent mouseEvent) {
    }
}
