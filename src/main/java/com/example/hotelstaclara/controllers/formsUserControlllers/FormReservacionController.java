package com.example.hotelstaclara.controllers.formsUserControlllers;

import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.controllers.UserControllers.USERreservaciones;
import com.example.hotelstaclara.database.HabiracionDAO;
import com.example.hotelstaclara.database.PagoDAO;
import com.example.hotelstaclara.database.ReservacionesDAO;
import com.example.hotelstaclara.model.*;
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
import java.util.List;
import java.util.Locale;

public class FormReservacionController {

    @FXML
    private Button but_Aceptar;

    @FXML
    private Label label_descuento;

    @FXML
    private Label label_diasEstadia;

    @FXML
    private Label label_precioHabitacion;

    @FXML
    private Label label_precioTotal;

    @FXML
    private Label label_tipo;

    @FXML
    private DatePicker pick_fechaInicio;

    @FXML
    private DatePicker pick_fechaSalida;

    @FXML
    private TextField tex_cliente;

    @FXML
    private TextField tex_habitacion;

    Rutas ruta = new Rutas();
    private String Estado_boton = "";
    private int IdReservacion;

    // listas
    private List<habitacion> listaHabitaciones;
    private List<Reservaciones> listaReservaciones;

    public void initialize() {
        // optener todas la lsitas de habitaciones y reservaciones
        listaHabitaciones = HabiracionDAO.TraeesHabitacions();
        listaReservaciones = ReservacionesDAO.traerReservaciones();
        // limpiar los campos y mostrar los dias
        limpiarCampos();
        mostrarDias();

        // eventos para actualizar en tiempo real
        tex_habitacion();
        tex_cliente();
    }

    @FXML
    void imgBack(MouseEvent event) {
        ruta.cerrarVentana(but_Aceptar);
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
       int id_cliente = reservacionesDAO.buscarUsuario(cliente),
           id_habitacion = habiracionDAO.buscarHabitacion(habitacion);


        // valida que si existe el cliente y la habitacion
        if (id_cliente == -1) {
            mesajesAlert.mostarAlertError("El cliente no se encuentra registrado");
            return;
        }else if(id_habitacion == -1) {
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
            ruta.cerrarVentana(but_Aceptar);
            ruta.pasarRutasRecepcionista("USERreservaciones", but_Aceptar);
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

        mostrarDias();
        tex_habitacion();
        tex_cliente();
        calcularPrecio();
        // obtener el precio de la habitacion

    }


    public void llenarDatosHabitacion(habitacion habitacion) {
        tex_habitacion.setText(habitacion.getNumero_habitacion());
        label_tipo.setText(habitacion.getTipo_habitacion());
        label_precioHabitacion.setText(String.valueOf(habitacion.getPrecio()));
        mostrarDias();
        label_diasEstadia.setText(String.valueOf(obtenerDias()));
        calcularPrecio();
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
        calcularPrecio();
    }



    public void limpiarCampos() {
        tex_habitacion.clear();
        tex_cliente.clear();
        // poner fecha actual
        LocalDate fechaActual = LocalDate.now();
        pick_fechaInicio.setValue(fechaActual);
        pick_fechaSalida.setValue(fechaActual);
        label_descuento.setText("0.0");
        label_diasEstadia.setText("1");
        label_precioHabitacion.setText("0.0");
        label_precioTotal.setText("0.00");
        calcularPrecio();
    }





    // mostar dias de reservacion
    public void mostrarDias() {
        pick_fechaInicio.valueProperty().addListener((obs, oldDate, newDate) -> {
            int dias = obtenerDias();
            label_diasEstadia.setText(String.valueOf(dias));
            calcularPrecio();
        });

        pick_fechaSalida.valueProperty().addListener((obs, oldDate, newDate) -> {
            int dias =  obtenerDias();
            label_diasEstadia.setText(String.valueOf(dias));
            calcularPrecio();
        });
    }

    public void tex_habitacion() {
        tex_habitacion.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // cuando pierde foco
                String habitacionNum = tex_habitacion.getText().trim();

                if (habitacionNum.isEmpty()) {
                    label_tipo.setText("Desconocido");
                    label_precioHabitacion.setText("0.00");
                    return;
                }
                // Buscar habitación en la lista
                habitacion habEncontrada = null;
                for (habitacion h : listaHabitaciones) {
                    if (h.getNumero_habitacion().equalsIgnoreCase(habitacionNum)) {
                        habEncontrada = h;
                        break;
                    }
                }

                if (habEncontrada == null) {
                    new MesajesAlert().mostarAlertError("La habitación no se encuentra registrada");
                    label_tipo.setText("Desconocido");
                    label_precioHabitacion.setText("0.00");
                } else {
                    label_tipo.setText(habEncontrada.getTipo_habitacion());
                    label_precioHabitacion.setText(String.valueOf(habEncontrada.getPrecio()));
                    calcularPrecio();
                }
            }
        });
    }



    public void tex_cliente() {
        ReservacionesDAO reservacionesDAO = new ReservacionesDAO();
        tex_cliente.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // cuando pierde foco
                String cliente = tex_cliente.getText().trim();

                if (cliente.isEmpty()) {
                    label_descuento.setText("0.0");
                    return;
                }

                Reservaciones reservaciones = null;
                for (Reservaciones r : listaReservaciones) {
                    if (r.getNombre_cliente().equalsIgnoreCase(cliente) || r.getApellido_cliente().equalsIgnoreCase(cliente) || r.getNombreClienteCopleto().equalsIgnoreCase(cliente)) {
                        reservaciones = r;
                        break;
                    }
                }

                if (reservaciones == null) {
                    label_descuento.setText("0.0");
                    new MesajesAlert().mostarAlertError("El cliente no se encuentra registrado");

                    calcularPrecio();
                } else {
                    double descuento = reservacionesDAO.tearDescuento(reservaciones.getId_cliente());
                    if (descuento == -1) {
                        label_descuento.setText("0.0");
                        calcularPrecio();
                        return;
                    }
                    label_descuento.setText(String.valueOf(descuento));
                    calcularPrecio();
                }
            }
        });
    }

    // metodo para calcular precio
    public void calcularPrecio() {
        if (label_descuento.equals("0.0")) {
            double precio = Double.parseDouble(label_precioHabitacion.getText());
            int dias = obtenerDias();
            double precioTotal = precio * dias;
            label_precioTotal.setText(String.valueOf(precioTotal));
            label_descuento.setText("0.0");
        } else {
            double precio = Double.parseDouble(label_precioHabitacion.getText());
            int dias = obtenerDias();
            double descuento = Double.parseDouble(label_descuento.getText());
            double  precioTotal =  (precio * obtenerDias()) - ((precio * obtenerDias()) * (descuento/100));
            label_precioTotal.setText(String.valueOf(precioTotal));
        }
    }


    // singronisar con UserReservaciones no funciona XD

    private USERreservaciones reservaciones;

    public void setReservaciones(USERreservaciones reservaciones) {
        this.reservaciones = reservaciones;
    }
}
