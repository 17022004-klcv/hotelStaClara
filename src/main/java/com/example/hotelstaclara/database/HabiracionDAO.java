package com.example.hotelstaclara.database;

import com.example.hotelstaclara.model.Estado_habitacion;
import com.example.hotelstaclara.model.habitacion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabiracionDAO {


    public void insertarHabitacion(habitacion h) throws SQLException {
        try( Connection conn = connection.getConnection()) {
            String sql = "INSERT INTO habitacion (numero_habitacion, tipo_habitacion, capacidad, precio, estado_habitacion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, h.getNumero_habitacion());
            stmt.setString(2, h.getTipo_habitacion());
            stmt.setInt(3, h.getCapacidad());
            stmt.setDouble(4, h.getPrecio());
            stmt.setString(5, h.getEstado_habitacion().name());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "La habitacion se ingreso correcta mente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: La habitation no se pudo ingresar" + e.getMessage());
        }
    }

    // optener datos del habitacion
    public static List<habitacion> TraeesHabitacions(){
        List<habitacion> lista = new ArrayList<>();

        String sql = "SELECT * FROM habitacion";
        try (Connection conn = connection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql);) {

            while(rs.next()){
                habitacion h = new habitacion();
                h.setId_habitacion(rs.getInt("id_habitacion"));
                h.setNumero_habitacion(rs.getString("numero_habitacion"));
                h.setTipo_habitacion(rs.getString("tipo_habitacion"));
                h.setCapacidad(rs.getInt("capacidad"));
                h.setPrecio(rs.getDouble("precio"));
                h.setEstado_habitacion(Estado_habitacion.valueOf(rs.getString("estado_habitacion")));
                lista.add(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Pues no se pude mi rey");
        }
        return lista;
    }


    // actualizar habitacion
    public void actualizarHabitacion(habitacion h) throws SQLException {
        String sql = "UPDATE habitacion SET numero_habitacion = ?, tipo_habitacion = ?, capacidad = ?, precio = ?, estado_habitacion = ? WHERE id_habitacion = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, h.getNumero_habitacion());
            stmt.setString(2, h.getTipo_habitacion());
            stmt.setInt(3, h.getCapacidad());
            stmt.setDouble(4, h.getPrecio());
            stmt.setString(5, h.getEstado_habitacion().name());
            stmt.setInt(6, h.getId_habitacion());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "La habitación se actualizó correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la habitación para actualizar");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: La habitación no se pudo actualizar. " + e.getMessage());
            e.printStackTrace(); // Para registrar el error en un log.
        }
    }

    // eliminar habitacion
    public static void eliminarHabitacion(int id) throws SQLException {
        String sql = "DELETE FROM habitacion WHERE id_habitacion = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "La habitación se elimino correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la habitación para eliminar");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: La habitación no se pudo eliminar. " + e.getMessage());
            e.printStackTrace(); // Para registrar el error en un log.
        }
    }



}
