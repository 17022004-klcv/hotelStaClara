package com.example.hotelstaclara.controllers.formsUserControlllers;

import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.controllers.UserControllers.USERreservaciones;
import com.example.hotelstaclara.database.HabiracionDAO;
import com.example.hotelstaclara.database.PagoDAO;
import com.example.hotelstaclara.database.ReservacionesDAO;
import com.example.hotelstaclara.model.Estado_habitacion;
import com.example.hotelstaclara.model.Estado_reservaciones;
import com.example.hotelstaclara.model.IdEmpleado;
import com.example.hotelstaclara.model.Reservaciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class FormReservacionController {

    @FXML
    public TextField tex_habitacion;

    @FXML
    public TextField tex_cliente;

    @FXML
    private Button but_Aceptar;

    @FXML
    private ImageView imgBack;

    @FXML
    private DatePicker pick_fechaInicio;

    @FXML
    private DatePicker pick_fechaSalida;

    @FXML
    private RadioButton rb_estandar;

    @FXML
    private RadioButton rb_premium;

    @FXML
    private RadioButton rb_royal;

    Rutas ruta = new Rutas();
    private String Estado_boton = "";
    private int IdReservacion;

    public void initialize() {
        limpiarCampos();
    }

    @FXML
    void imgBack(MouseEvent event) {
        ruta.cerrarVentana(but_Aceptar);
    }

    @FXML
    void rb_estandar(ActionEvent event) {
    }

    @FXML
    void rb_premium(ActionEvent event) {
    }

    @FXML
    void rb_royal(ActionEvent event) {

    }

    public void tex_habitacion(ActionEvent actionEvent) {
    }

    @FXML
    void but_Aceptar(ActionEvent event) {

        // optener todas las instancias
        MesajesAlert mesajesAlert = new MesajesAlert();
        ReservacionesDAO reservacionesDAO = new ReservacionesDAO();
        HabiracionDAO habiracionDAO = new HabiracionDAO();
        IdEmpleado idEmpleado = new IdEmpleado();

        // optener todos lo datos de la reservacion
        String habitacion = tex_habitacion.getText();
        String cliente = tex_cliente.getText().trim();

        // optener la fechas
        LocalDate fechaIni = pick_fechaInicio.getValue();
        java.sql.Date fecha_inicio = java.sql.Date.valueOf(fechaIni);

        LocalDate fechaSal = pick_fechaSalida.getValue();
        java.sql.Date fecha_salida = java.sql.Date.valueOf(fechaSal);

        // optener el id del cliente y la habitacion
       int id_cliente = reservacionesDAO.buscarUsuario(cliente);
       int id_habitacion = habiracionDAO.buscarHabitacion(habitacion);

        // valida que si existe el cliente y la habitacion
        if (id_cliente == -1) {
            mesajesAlert.mostarAlertError("El cliente no se encuentra registrado");
            return;
        }
        if (id_habitacion == -1) {
            mesajesAlert.mostarAlertError("La habitacion no se encuentra disponible");
            return;
        }

        // optener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        java.sql.Date fecha_actual = java.sql.Date.valueOf(fechaActual);

        if (!Estado_boton.equals("Edit")) {
            // crear la reservacion
            int id_reservacion =  reservacionesDAO.guardarReservaciones(new Reservaciones(0, fecha_actual, fecha_inicio, fecha_salida, id_cliente, idEmpleado.getIdEmpleado(),id_habitacion, Estado_reservaciones.activa));
            opcionPago(id_habitacion, id_cliente, id_reservacion, "Add");
            limpiarCampos();
        }else {
            // editar la reservacion
            reservacionesDAO.actualizarEstadoReservacion(new Reservaciones(IdReservacion, fecha_actual, fecha_inicio, fecha_salida, id_cliente, idEmpleado.getIdEmpleado(),id_habitacion, Estado_reservaciones.activa));
            opcionPago(id_habitacion, id_cliente, IdReservacion, "Edit");

            ruta.cerrarVentana(but_Aceptar);
        }
    }

    private int obtenerDias() {
        MesajesAlert mesajesAlert = new MesajesAlert();

        LocalDate fechaInicio = pick_fechaInicio.getValue();
        LocalDate fechaFin = pick_fechaSalida.getValue();

        if (fechaInicio != null && pick_fechaSalida != null) {
            long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin) +1;

            if (dias >= 0) {
                return (int) dias;
            } else {
                mesajesAlert.mostarAlertError("La fecha de salida debe ser posterior a la fecha de inicio.");
                return 0;
            }
        } else {
            mesajesAlert.mostarAlertError("Selecciona ambas fechas.");
        }
        return 0;
    }


    public void llenarDatos(Reservaciones reservaciones, String Estado_boton) {
        this.Estado_boton = Estado_boton;
        this.IdReservacion = reservaciones.getId_reservacion();
        but_Aceptar.setText("Editar Reservacion");
        tex_habitacion.setText(reservaciones.getNumero_habitacion());
        tex_cliente.setText(reservaciones.getNombre_cliente());

        // obtener la fecha de inicio y la fecha de salida
        pick_fechaInicio.setValue(reservaciones.getFecha_ingreso().toLocalDate());
        pick_fechaSalida.setValue(reservaciones.getFecha_salida().toLocalDate());

    }



    private  void  opcionPago (int id_habitacion, int id_cliente, int id_reservacion, String Estado_opcion) {
        HabiracionDAO habiracionDAO = new HabiracionDAO();
        ReservacionesDAO reservacionesDAO = new ReservacionesDAO();
        PagoDAO pagoDAO = new PagoDAO();

        double monto = habiracionDAO.traerMontoHabitacion(id_habitacion);
        double descuento = reservacionesDAO.tearDescuento(id_cliente);

        // monto con descuento
        double montoDescuento = 0;
        if (descuento != -1) {
            montoDescuento =  (monto * obtenerDias()) - ((monto * obtenerDias()) * (descuento/100));
        }

        if (Estado_opcion.equals("Add")) {
            pagoDAO.insertarPago(new BigDecimal(monto * obtenerDias()), new BigDecimal(montoDescuento), LocalDate.now(), "Efectivo", id_reservacion, id_cliente);
        } else if (Estado_opcion.equals("Edit")) {
            int id_pago = pagoDAO.trarIDPago(id_reservacion);
            pagoDAO.actualizarPago(id_pago,new BigDecimal(monto * obtenerDias()), new BigDecimal(montoDescuento), LocalDate.now(), "Efectivo", id_reservacion, id_cliente);
        }

    }













    public void limpiarCampos() {
        tex_habitacion.clear();
        tex_cliente.clear();
        // poner fecha actual
        LocalDate fechaActual = LocalDate.now();
        pick_fechaInicio.setValue(fechaActual);
        pick_fechaSalida.setValue(fechaActual);
    }

    // singronisar con UserReservaciones

    private USERreservaciones reservaciones;

    public void setReservaciones(USERreservaciones reservaciones) {
        this.reservaciones = reservaciones;
    }



}
