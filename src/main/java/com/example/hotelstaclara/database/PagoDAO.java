package com.example.hotelstaclara.database;

import com.example.hotelstaclara.Recursos.MesajesAlert;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.sql.*;


public class PagoDAO {
    // insertar pago

    public void insertarPago(BigDecimal monto, BigDecimal monto_con_descuento, LocalDate fecha_pago, String metodo_de_pago, int id_reservacion, int id_cliente) {
        MesajesAlert mesajesAlert = new MesajesAlert();

        String sql = """
                INSERT INTO pago (monto, monto_con_descuento, fecha_pago, metodo_de_pago, id_reservacion, id_cliente) VALUES
                (?, ?,?, ?, ?, ?);
                """;

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, monto);
            stmt.setBigDecimal(2, monto_con_descuento);
            stmt.setDate(3, java.sql.Date.valueOf(fecha_pago));
            stmt.setString(4, metodo_de_pago);
            stmt.setInt(5, id_reservacion);
            stmt.setInt(6, id_cliente);

            stmt.executeUpdate();
            mesajesAlert.mostarAlertWARNING( "El pago se insertó correctamente.");

        } catch (SQLException e) {
            mesajesAlert.mostarAlertError("El pago no se pudo ingresar: " + e.getMessage());
        }
    }

}
