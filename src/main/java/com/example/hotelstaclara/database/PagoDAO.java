package com.example.hotelstaclara.database;

import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.model.Reservaciones;

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
            mesajesAlert.mostarAlertWARNING("El pago se insertó correctamente.");

        } catch (SQLException e) {
            mesajesAlert.mostarAlertError("El pago no se pudo ingresar: " + e.getMessage());
        }
    }




    public int trarIDPago(int id_reservacion) {
        MesajesAlert mesajesAlert = new MesajesAlert();

        String sql = """
                    select
                		p.id_pago
                    from reservacion r
                    JOIN pago p On p.id_reservacion = r.id_reservacion
                    where r.id_reservacion = ?;
                """;

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_reservacion);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                mesajesAlert.mostarAlertWARNING("El id del pago es: " + rs.getInt("id_pago") + ".");
                return rs.getInt("id_pago");
            } else {
                mesajesAlert.mostarAlertError("El id del pago no se pudo encontrar.");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    // actualizar pago
    public void actualizarPago(int id_pago, BigDecimal monto, BigDecimal monto_con_descuento, LocalDate fecha_pago, String metodo_de_pago, int id_reservacion, int id_cliente) {
        MesajesAlert mesajesAlert = new MesajesAlert();

        String sql = """
                UPDATE pago SET monto = ?, monto_con_descuento = ?, fecha_pago = ?, metodo_de_pago = ?, id_reservacion = ?, id_cliente = ? WHERE id_pago = ?
                """;

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, monto);
            stmt.setBigDecimal(2, monto_con_descuento);
            stmt.setDate(3, java.sql.Date.valueOf(fecha_pago));
            stmt.setString(4, metodo_de_pago);
            stmt.setInt(5, id_reservacion);
            stmt.setInt(6, id_cliente);
            stmt.setInt(7, id_pago);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                mesajesAlert.mostarAlertWARNING("El pago se actualizó correctamente.");
            } else {
                mesajesAlert.mostarAlertError("El pago no se pudo actualizar.");
            }

        } catch (SQLException e) {
            mesajesAlert.mostarAlertError("El pago no se pudo actualizar =( : " + e.getMessage());
        }
    }

}
