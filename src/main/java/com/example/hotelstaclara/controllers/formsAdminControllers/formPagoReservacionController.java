package com.example.hotelstaclara.controllers.formsAdminControllers;

import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.Recursos.Rutas;
import com.example.hotelstaclara.controllers.AdminController.AdminPagosController;
import com.example.hotelstaclara.controllers.UserControllers.USERpagos;
import com.example.hotelstaclara.database.HabiracionDAO;
import com.example.hotelstaclara.database.PagoDAO;
import com.example.hotelstaclara.database.ReservacionesDAO;
import com.example.hotelstaclara.model.FacturaReservacionPago;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.time.LocalDate;

public class formPagoReservacionController {

    @FXML
    private Button btnPagar;

    @FXML
    private ComboBox<?> cb_metodo_pago;

    @FXML
    private Label lbl_total;

    @FXML
    private TextField txt_cambio;

    @FXML
    private TextField txt_cliente;

    @FXML
    private TextField txt_descuento;

    @FXML
    private TextField txt_dinero_recibido;

    @FXML
    private TextField txt_empleado;

    @FXML
    private TextField txt_id_reservacion;

    @FXML
    private TextField txt_membresia;

    @FXML
    private TextField txt_precio_habitacion;


    Rutas ruta = new Rutas();
    PagoDAO dao = new PagoDAO();
    HabiracionDAO habitacionesDAO = new HabiracionDAO();
    MesajesAlert mesajesAlert = new MesajesAlert();


    FacturaReservacionPago detalle;

    public void initialize() {
        // obtener id pago y ver de que vista viene si de admin o de recepcionista
        int idPagoSeleccionado = vericarView();

        detalle = dao.obtenerDetalleReservacionPorId(idPagoSeleccionado);
        llenarCampos();
        validarDineroRecibido();
        calcularCambio();
        txt_dinero_recibido.setEditable(false);

        // reiniciar variables
        AdminPagosController.id_pago = -1;
        USERpagos.id_pagoReservacion = -1;
    }

    // ver de que vista viene
    private int vericarView() {
        int idPagoSeleccionado = 0;
        if (AdminPagosController.id_pago == -1) {
           return idPagoSeleccionado = USERpagos.id_pagoReservacion;
        }

        if (USERpagos.id_pagoReservacion == -1) {
           return idPagoSeleccionado = AdminPagosController.id_pago;
        }
        return idPagoSeleccionado;
    }

    @FXML
    void imgBack(MouseEvent event) {
        ruta.cerrarVentana(btnPagar);
    }

    @FXML
    void btnPagar(MouseEvent event) {
        dao.actualizarPagoYReservacion(AdminPagosController.id_pago, LocalDate.now(), obtenerMetodoPago(), 1, "pagada");
        habitacionesDAO.editarEstadoHabitacion(detalle.getIdHabitacion(), "sucia");
        ruta.cerrarVentana(btnPagar);
    }

    private void llenarCampos() {
        if (detalle == null) {
            mesajesAlert.mostarAlertError("No se pudo encontrar el detalle de la reservacion XD");
            return;
        }

        txt_id_reservacion.setText(String.valueOf(detalle.getIdReservacion()));
       txt_cliente.setText(detalle.getNombreCliente());
       txt_empleado.setText(detalle.getNombreEmpleado());
       txt_precio_habitacion.setText(String.valueOf(detalle.getPrecioHabitacion()));
       txt_membresia.setText(detalle.getNombreMembresia());
       txt_descuento.setText(String.valueOf(detalle.getDescuento()));
       lbl_total.setText(String.valueOf(detalle.getMontoTotalPagar()));

    }

    // obter metodo de pago
    public String obtenerMetodoPago() {
        return cb_metodo_pago.getValue().toString();
    }

    //evento para calcular el cambio
    public void validarDineroRecibido() {

        cb_metodo_pago.setOnAction(event -> {
            String metodoSeleccionado = obtenerMetodoPago();
            String dineroRecibidoString = txt_dinero_recibido.getText();

            if ("Tarjeta".equals(metodoSeleccionado)){
                txt_dinero_recibido.setEditable(false);
                txt_dinero_recibido.setText("");
                txt_cambio.setText("");
            }else {
                txt_dinero_recibido.setEditable(true);
            }
        });
    }


    public void calcularCambio() {
            txt_dinero_recibido.focusedProperty().addListener((observable, oldValue, newValue) -> {
                String metodoSeleccionado = obtenerMetodoPago();
                if (!newValue) {

                    if(detalle.getMontoTotalPagar() >= Double.parseDouble(txt_dinero_recibido.getText())) {
                        mesajesAlert.mostarAlertError("El dinero recibido es insuficiente");
                        return;
                    }

                    if ("Tarjeta".equals(metodoSeleccionado)) {
                        txt_cambio.setText("");
                    } else {
                        String dineroRecibidoString = txt_dinero_recibido.getText();
                        double dineroRecibido = Double.parseDouble(dineroRecibidoString);
                        double total = Double.parseDouble(lbl_total.getText());
                        double cambio = dineroRecibido - total;
                        txt_cambio.setText(String.valueOf(cambio));
                        JOptionPane.showMessageDialog(null, "Cambio: " + cambio);
                    }
                }
            });
    }
}
