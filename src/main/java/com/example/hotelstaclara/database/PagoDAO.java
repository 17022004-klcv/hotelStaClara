package com.example.hotelstaclara.database;

import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.model.FacturaReservacionPago;
import com.example.hotelstaclara.model.PagoTabla;
import com.example.hotelstaclara.model.Reservaciones;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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



    // traer pagos para la tabla
    public List<PagoTabla> traerPagoTabla() {
        List<PagoTabla> lista = new ArrayList<>();
        MesajesAlert mesajesAlert = new MesajesAlert();

        String sql = """
                            SELECT
                                CONCAT(c.nombre_cliente, ' ', c.apellido_cliente) AS nombre_cliente,
                                CONCAT(e.nombre_empleado, ' ', e.apellido_empleado) AS nombre_empleado,
                                h.numero_habitacion AS habitacion,
                                r.estado_reserva AS estado,
                                p.monto AS monto,
                                p.id_pago,
                                r.id_reservacion
                            FROM pago p
                            JOIN reservacion r ON p.id_reservacion = r.id_reservacion
                            JOIN cliente c ON p.id_cliente = c.id_cliente
                            JOIN empleado e ON r.id_empleado = e.id_empleado
                            JOIN habitacion h ON r.id_habitacion = h.id_habitacion
                            WHERE r.estado_reserva = 'activa';
                            """;

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String cliente = rs.getString("nombre_cliente");
                String empleado = rs.getString("nombre_empleado");
                String habitacion = rs.getString("habitacion");
                String estado = rs.getString("estado");
                double monto = rs.getDouble("monto");
                int id_pago = rs.getInt("id_pago");
                int id_reservacion = rs.getInt("id_reservacion");

                PagoTabla pagoTabla = new PagoTabla(cliente, empleado, habitacion, estado, monto, id_pago, id_reservacion);
                lista.add(pagoTabla);
            }
            return lista;

        } catch (SQLException e) {
            mesajesAlert.mostarAlertError("Error al obtener pagos: " + e.getMessage());
        }

        return lista;
    }



    public FacturaReservacionPago obtenerDetalleReservacionPorId(int idPago) {
        String sql = """
        SELECT
            r.id_reservacion,
            CONCAT(c.nombre_cliente, ' ', c.apellido_cliente) AS nombre_cliente,
            CONCAT(e.nombre_empleado, ' ', e.apellido_empleado) AS nombre_empleado,
            h.precio AS precio_habitacion,
            m.nombre_membresia,
            m.descuento,
            p.monto AS dineroTotaPagar,
            c.id_cliente AS idCliente,
            h.id_habitacion As idHabitacion
        FROM reservacion r
        JOIN cliente c ON r.id_cliente = c.id_cliente
        JOIN habitacion h ON r.id_habitacion = h.id_habitacion
        JOIN empleado e ON r.id_empleado = e.id_empleado
        LEFT JOIN cliente_membresia cm ON c.id_cliente = cm.id_cliente
        LEFT JOIN membresia m ON cm.id_membresia = m.id_membresia
        JOIN pago p ON r.id_reservacion = p.id_reservacion
        WHERE r.estado_reserva = 'activa' AND p.id_pago = ?;
        """;

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPago);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new FacturaReservacionPago(
                        rs.getInt("id_reservacion"),
                        rs.getString("nombre_cliente"),
                        rs.getString("nombre_empleado"),
                        rs.getDouble("precio_habitacion"),
                        rs.getString("nombre_membresia"),
                        rs.getDouble("descuento"),
                        rs.getDouble("dineroTotaPagar"),
                        rs.getInt("idCliente"),
                        rs.getInt("idHabitacion")
                );
            }

        } catch (SQLException e) {
            new MesajesAlert().mostarAlertError("Error al consultar detalle de la reservación: hijo de la luna " + e.getMessage());
        }

        return null;
    }



    public void actualizarPagoYReservacion(int idPago, LocalDate fechaPago, String metodoPago, int idEmpleado, String estado) {
        MesajesAlert mesajesAlert = new MesajesAlert();

        String actualizarPagoSQL = "UPDATE pago SET fecha_pago = ?, metodo_de_pago = ? WHERE id_pago = ?";
        String actualizarReservacionSQL = "UPDATE reservacion SET id_empleado = ?, estado_reserva = ? WHERE id_reservacion = (SELECT id_reservacion FROM pago WHERE id_pago = ?)";

        try (Connection conn = connection.getConnection()) {
            conn.setAutoCommit(false); // Transacción

            try (
                    PreparedStatement stmtPago = conn.prepareStatement(actualizarPagoSQL);
                    PreparedStatement stmtReservacion = conn.prepareStatement(actualizarReservacionSQL)
            ) {
                // Actualizar tabla pago
                stmtPago.setDate(1, Date.valueOf(fechaPago));
                stmtPago.setString(2, metodoPago);
                stmtPago.setInt(3, idPago);
                stmtPago.executeUpdate();

                // Actualizar tabla reservacion
                stmtReservacion.setInt(1, idEmpleado);
                stmtReservacion.setString(2, estado);
                stmtReservacion.setInt(3, idPago);
                stmtReservacion.executeUpdate();

                conn.commit();
                mesajesAlert.mostarAlertWARNING("El pago y la reservación se actualizaron correctamente.");

            } catch (SQLException e) {
                conn.rollback(); // Si algo falla, deshacer cambios
                mesajesAlert.mostarAlertError("Error al actualizar: " + e.getMessage());
            }

        } catch (SQLException ex) {
            mesajesAlert.mostarAlertError("Fallo la conexión o rollback: " + ex.getMessage());
        }
    }
}
