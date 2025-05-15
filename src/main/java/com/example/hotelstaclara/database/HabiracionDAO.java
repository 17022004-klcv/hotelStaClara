package com.example.hotelstaclara.database;

import com.example.hotelstaclara.Alert.Alert;
import com.example.hotelstaclara.model.Estado_habitacion;
import com.example.hotelstaclara.model.habitacion;

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

            Alert.showInfoAlert("Exito", "Exito", "La habitacion se ingreso correcta mente");
        } catch (SQLException e) {
            Alert.showErrorAlert("Error", "Error", "La habitacion no se pudo ingresar" + e.getMessage());
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
            Alert.showErrorAlert("Error", "Error", "La habitacion no se pudo ingresar" + e.getMessage());
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
                Alert.showInfoAlert("Exito", "Exito", "La habitacion se actualizo correctamente");
            } else {
                Alert.showErrorAlert("Error", "Error", "La habitacion no se pudo actualizar");
            }
        } catch (SQLException e) {
            Alert.showErrorAlert("Error", "Error", "La habitacion no se pudo actualizar" + e.getMessage());
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
                Alert.showInfoAlert("Exito", "Exito", "La habitacion se elimino correctamente");
            } else {
                Alert.showErrorAlert("Error", "Error", "La habitacion no se pudo eliminar");
            }
        } catch (SQLException e) {
            Alert.showErrorAlert("Error", "Error", "La habitacion no se pudo eliminar" + e.getMessage());
            e.printStackTrace();
        }
    }

    public int buscarHabitacion(String numero_habitacion){
        String sql = "select id_habitacion from habitacion where numero_habitacion =? && estado_habitacion = 'disponible';";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numero_habitacion);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_habitacion");
            }else{
                return -1;
            }
        }catch (SQLException ex) {
            Alert.showErrorAlert("Error", "Error", "La habitacion no se encontro" + ex.getMessage());
            ex.printStackTrace();
        }
        return -1;
    }

    // traer monto de habitacion

    public double traerMontoHabitacion(int id_habitacion) {
        String sql = "SELECT precio FROM habitacion WHERE id_habitacion = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_habitacion);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("precio");
            } else {
                return 0.0;
            }
        } catch (SQLException e) {
            Alert.showErrorAlert("Error", "Error", "La habitacion no se pudo ingresar" + e.getMessage());
            e.printStackTrace();
            return 0.0;
        }
    }

}
