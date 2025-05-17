package com.example.hotelstaclara.database;

import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.model.Estado_habitacion;
import com.example.hotelstaclara.model.Estado_reservaciones;
import com.example.hotelstaclara.model.Reservaciones;

import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservacionesDAO {

    MesajesAlert mesajesAlert = new MesajesAlert();

    public int guardarReservaciones(Reservaciones reservaciones) {

        String sql = "INSERT INTO reservacion (fecha_reserva, fecha_ingreso, fecha_salida, id_cliente, id_empleado, id_habitacion, estado_reserva) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, reservaciones.getFecha_reserva());
            stmt.setDate(2, reservaciones.getFecha_ingreso());
            stmt.setDate(3, reservaciones.getFecha_salida());
            stmt.setInt(4, reservaciones.getId_cliente());
            stmt.setInt(5, reservaciones.getId_empleado());
            stmt.setInt(6, reservaciones.getId_habitacion());
            stmt.setString(7, reservaciones.getEstado_reservacion().name());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);
                        mesajesAlert.mostarAlertWARNING("Reservación ingresada corectamente.");                        return idGenerado;
                    }
                }
            }

            mesajesAlert.mostarAlertError( "La reservación se insertó, pero no se pudo obtener el ID.");
        } catch (SQLException ex) {
            mesajesAlert.mostarAlertError("La reservación no se pudo ingresar: " + ex.getMessage());
        }

        return -1;
    }


    public int buscarUsuario(String userDUI) {
        String sql = """
                SELECT id_cliente
                FROM cliente cl
                WHERE cl.nombre_cliente = ?
                   OR cl.DUI_cliente = ?
                   OR CONCAT(cl.nombre_cliente, ' ', cl.apellido_cliente) = ?;
                                
                """;
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userDUI);
            stmt.setString(2, userDUI);
            stmt.setString(3, userDUI);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_cliente");
            } else {
                return -1;
            }

        } catch (SQLException ex) {
            mesajesAlert.mostarAlertError("El usuario no se pudo encontrar" + ex.getMessage() + ex);
        }
        return -1;
    }


    // traer menrecioas de habitacion

    public double tearDescuento(int id_cliente) {
        String sql = """
                select
                	descuento
                from cliente_membresia cm
                join membresia m On cm.id_membresia = m.id_membresia
                join cliente c On cm.id_cliente = c.id_cliente
                where c.id_cliente = ?;
                """;
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_cliente);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("descuento");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            mesajesAlert.mostarAlertError("El usuario no se pudo encontrar" + e.getMessage() + e);
        }
        return -1;
    }

    public static List<Reservaciones> traerReservaciones(){
        MesajesAlert mesajesAlert = new MesajesAlert();

        List<Reservaciones> reservaciones = new ArrayList<>();
        String sql = """
                select
                c.fecha_reserva,
                c.fecha_ingreso,
                c.fecha_salida,
                concat(cl.nombre_cliente, ' ', cl.apellido_cliente) As nombreCliente,
                concat(e.nombre_empleado, ' ', e.apellido_empleado) As nombreEmpreado,
                h.numero_habitacion,
                c.id_reservacion
                from reservacion c
                JOIN cliente cl On c.id_cliente = cl.id_cliente
                join empleado e On c.id_empleado = e.id_empleado
                join habitacion h On c.id_habitacion = h.id_habitacion;
                """;
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reservaciones r = new Reservaciones();
                r.setFecha_reserva(rs.getDate("fecha_reserva"));
                r.setFecha_ingreso(rs.getDate("fecha_ingreso"));
                r.setFecha_salida(rs.getDate("fecha_salida"));
                r.setNombre_cliente(rs.getString("nombreCliente"));
                r.setNombre_empleado(rs.getString("nombreEmpreado"));
                r.setNumero_habitacion(rs.getString("numero_habitacion"));
                r.setId_reservacion(rs.getInt("id_reservacion"));
                reservaciones.add(r);
            }
        } catch (SQLException e) {
            mesajesAlert.mostarAlertError( "El usuario no se pudo encontrar" + e.getMessage() + e);
        }
        return reservaciones;
    }

    // actualizar estado de la reservacion
    public void actualizarEstadoReservacion(Reservaciones r) {
        String sql = "UPDATE reservacion SET fecha_ingreso = ?, fecha_salida = ?, id_cliente = ?, id_habitacion = ? WHERE id_reservacion = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, r.getFecha_ingreso());
            stmt.setDate(2, r.getFecha_salida());
            stmt.setInt(3, r.getId_cliente());
            stmt.setInt(4, r.getId_habitacion());
            stmt.setInt(5, r.getId_reservacion());

            if (stmt.executeUpdate() > 0) {
                mesajesAlert.mostarAlertWARNING("Reservación actualizada corectamente.");
            }else{
                mesajesAlert.mostarAlertError("La reservación no se pudo actualizar.");
            }

        } catch (SQLException e) {
            mesajesAlert.mostarAlertError( "El usuario no se pudo encontrar" + e.getMessage() + e);
        }
    }
}
