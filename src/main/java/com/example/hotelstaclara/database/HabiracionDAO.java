package com.example.hotelstaclara.database;

import com.example.hotelstaclara.model.habitacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
