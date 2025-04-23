package com.example.hotelstaclara.validations;

import com.example.hotelstaclara.Alert.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;

import java.awt.*;
import java.time.LocalDate;

public class validaciones {

    public static boolean validarNombreApellido(TextField nombre, TextField apellido) {
        //Valida simplemente que no esten vacios
        if (nombre.getText().isEmpty() || apellido.getText().isEmpty()) {
            Alert.showWarningAlert("Campos vacíos", null, "Por favor ingrese el nombre y apellido.");
            return false;
        }
        return true;
    }

    public static boolean validarCorreo(TextField correo) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        // Lo valida de la siguiente forma user@Domimio.(net, com) etc
        if (correo.getText().isEmpty() || !correo.getText().matches(emailPattern)) {
            Alert.showWarningAlert("Correo inválido", null, "Ingrese un correo válido.");
            return false;
        }
        return true;
    }

    public static boolean validarTelefono(TextField telefono) {
        String phonePattern = "^\\d{8,10}$"; // 8-10 dígitos
        if (telefono.getText().isEmpty() || !telefono.getText().matches(phonePattern)) {
            Alert.showWarningAlert("Teléfono inválido", null, "Ingrese un teléfono válido (8-10 dígitos).");
            return false;
        }
        return true;
    }

    public static boolean validarDUI(TextField dui) {
        String duiPattern = "^\\d{8}-\\d$"; // Formato 00000000-0
        if (dui.getText().isEmpty() || !dui.getText().matches(duiPattern)) {
            Alert.showWarningAlert("DUI inválido", null, "Ingrese un DUI válido (00000000-0).");
            return false;
        }
        return true;
    }

    public static boolean validarDireccion(TextField direccion) {
        if (direccion.getText().isEmpty()) {
            Alert.showWarningAlert("Campo vacío", null, "Por favor ingrese una dirección.");
            return false;
        }
        return true;
    }

    public static boolean validarCombo(ComboBox<?> combo, String nombreCampo) {
        if (combo == null || combo.getSelectionModel().isEmpty()) {
            Alert.showWarningAlert("Selección Requerida", null, "Por favor selecciona una opción en " + nombreCampo + ".");
            return false;
        }
        return true;
    }

    public static boolean validarCargoSeleccionado(ToggleGroup rdoCargo) {
        if (rdoCargo.getSelectedToggle() == null) {
            Alert.showWarningAlert("Cargo no seleccionado", null, "Por favor seleccione un cargo para el empleado.");
            return false;
        }
        return true;
    }

    public static boolean validarEstadoSeleccionado(ToggleGroup rdoEstado) {
        if (rdoEstado.getSelectedToggle() == null) {
            Alert.showWarningAlert("Estado no seleccionado", null, "Por favor seleccione el estado del empleado (Activo/Inactivo).");
            return false;
        }
        return true;
    }

    public static boolean validarNumeroHabitacion(TextField txtN_Habitacion) {
        String numeroHabitacion = txtN_Habitacion.getText();
        if (!numeroHabitacion.matches("[A-C]\\d{2,3}")) {  // Letra A, B o C seguida de 2 o 3 dígitos
            Alert.showWarningAlert("Número de Habitación Inválido", null, "El número de habitación debe seguir el formato: A00, B00, C141.");
            return false;
        }
        return true;
    }

    // Validación para Precio (Solo números mayores a 0)
    public static boolean validarPrecio(TextField txtPrecio) {
        String precio = txtPrecio.getText();
        try {
            double precioValue = Double.parseDouble(precio);
            if (precioValue <= 0) {
                Alert.showWarningAlert("Precio Inválido", null, "El precio debe ser un número mayor que 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            Alert.showWarningAlert("Precio Inválido", null, "Por favor ingrese un número válido para el precio.");
            return false;
        }
        return true;
    }

    // Validación para Capacidad (Solo números)
    public static boolean validarCapacidad(TextField txtCapacidad) {
        String capacidad = txtCapacidad.getText();
        if (!capacidad.matches("\\d+")) {  // Verifica que sea solo números
            Alert.showWarningAlert("Capacidad Inválida", null, "La capacidad debe ser un número.");
            return false;
        }
        return true;
    }

    // Validación para Tipo de Habitación Seleccionado
    public static boolean validarTipoHabitacionSeleccionado(ToggleGroup rdoTipo) {
        if (rdoTipo.getSelectedToggle() == null) {
            Alert.showWarningAlert("Tipo de Habitación No Seleccionado", null, "Por favor seleccione el tipo de habitación.");
            return false;
        }
        return true;
    }

    // Validación para las fechas (Fecha Inicio y Fecha Fin)
    public static boolean validarFechas(DatePicker dpFechaInicio, DatePicker dpFechaFin) {
        // Verificar si alguna de las fechas es nula
        if (dpFechaInicio.getValue() == null || dpFechaFin.getValue() == null) {
            Alert.showWarningAlert("Fecha No Seleccionada", null, "Por favor seleccione ambas fechas.");
            return false;
        }

        // Verificar si la fecha de inicio es posterior a la fecha de fin
        if (dpFechaInicio.getValue().isAfter(dpFechaFin.getValue())) {
            Alert.showWarningAlert("Fechas Inválidas", null, "La fecha de inicio no puede ser posterior a la fecha de fin.");
            return false;
        }

        // Verificar si la fecha de inicio es posterior a la fecha actual
        LocalDate fechaHoy = LocalDate.now(); // Obtener la fecha actual
        if (dpFechaInicio.getValue().isBefore(fechaHoy)) {
            Alert.showWarningAlert("Fecha de Inicio Inválida", null, "La fecha de inicio no puede ser anterior a la fecha de hoy.");
            return false;
        }

        return true;
    }

    // Validación para cliente seleccionado
    public static boolean validarClienteSeleccionado(ComboBox<?> cboCliente) {
        if (cboCliente.getValue() == null) {
            Alert.showWarningAlert("Cliente No Seleccionado", null, "Por favor seleccione un cliente.");
            return false;
        }
        return true;
    }

    // Validación para empleado seleccionado
    public static boolean validarEmpleadoSeleccionado(ComboBox<?> cboEmpleado) {
        if (cboEmpleado.getValue() == null) {
            Alert.showWarningAlert("Empleado No Seleccionado", null, "Por favor seleccione un empleado.");
            return false;
        }
        return true;
    }

    // Validación para habitación seleccionada
    public static boolean validarHabitacionSeleccionada(ComboBox<?> cboHabitacion) {
        if (cboHabitacion.getValue() == null) {
            Alert.showWarningAlert("Habitación No Seleccionada", null, "Por favor seleccione una habitación.");
            return false;
        }
        return true;
    }

}
