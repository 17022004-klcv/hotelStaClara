package com.example.hotelstaclara.database;

import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.model.empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class empleadoDAO {
    static MesajesAlert alert=new MesajesAlert();


    public void insertarEmpleado(empleado empleado) {
        String sql = """
        INSERT INTO empleado
        (nombre_empleado, apellido_empleado, DUI_empleado, 
         id_contacto, id_cargo, estado_empleado)
        VALUES (?, ?, ?, ?, ?, ?)""";

        try (Connection con = connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (empleado == null) {
                throw new IllegalArgumentException("El empleado no puede ser nulo");
            }

            ps.setString(1, empleado.getNombre_empleado());
            ps.setString(2, empleado.getApellido_empleado());
            ps.setString(3, empleado.getDUI_empleado());
            ps.setInt(4, empleado.getId_contacto());
            ps.setInt(5, empleado.getId_cargo());
            ps.setInt(6, empleado.getEstado_empleado());

            int filasAfectadas = ps.executeUpdate();

            // Obtener el ID generado si es necesario
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    empleado.setId_empleado(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            alert.showErrorAlert("ERROR", null,
                    "Ocurrió un error al ingresar el empleado: " + e.getMessage());
        }
    }

    //SELECT PARA MOSTRAR EN EL CRUD
    public static ObservableList<Map> getEmpleados(){
        ObservableList<Map> lista = FXCollections.observableArrayList();
        String query = """
            SELECT
                e.id_empleado,
                e.nombre_empleado,
                e.apellido_empleado,
                co.telefono_1,
                co.direccion,
                e.DUI_empleado,
                ema.email,
                ca.nombre_cargo,
                e.estado_empleado
            FROM empleado AS e
                     INNER JOIN email AS ema ON e.id_empleado = ema.id_empleado
                     INNER JOIN contacto AS co ON e.id_contacto = co.id_contacto
                     INNER JOIN cargo AS ca ON e.id_cargo = ca.id_cargo
            ;""";
        Connection con = connection.getConnection();

        if(con != null){
            try(Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query)){

                while(rs.next()){
                    Map Empleado = new HashMap();
                    Empleado.put("id_empleado",rs.getInt("id_empleado"));
                    Empleado.put("nombre_empleado", rs.getString("nombre_empleado"));
                    Empleado.put("apellido_empleado", rs.getString("apellido_empleado"));
                    Empleado.put("telefono_1", rs.getString("telefono_1"));
                    Empleado.put("direccion", rs.getString("direccion"));
                    Empleado.put("DUI_empleado", rs.getString("DUI_empleado"));
                    Empleado.put("email", rs.getString("email"));
                    Empleado.put("nombre_cargo", rs.getString("nombre_cargo"));
                    Empleado.put("estado_empleado", rs.getInt("estado_empleado"));
                    lista.add(Empleado);
                }

                return lista;

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public void DELETE_EMPLEADO(String email){
        String sql = """
                DELETE FROM contacto
                WHERE id_contacto = (
                    SELECT id_empleado
                    FROM email
                    WHERE email = ?
                );
                """;

        Connection con = connection.getConnection();

        if (con != null) {
            try{
                PreparedStatement pstm= con.prepareStatement(sql);
                pstm.setString(1, email);
                pstm.execute();
            } catch (SQLException e) {
                alert.showErrorAlert("Error al eliminar",null,"No se puede eliminar el cliente");
            }
        }
    }

    public  ObservableList<Map> get_datos_Empleado(String email) {

        ObservableList<Map> lista = FXCollections.observableArrayList();
        String query = """
            SELECT
                e.id_empleado,
                e.nombre_empleado,
                e.apellido_empleado,
                co.telefono_1,
                co.direccion,
                e.DUI_empleado,
                ema.email,
                ca.nombre_cargo,
                e.estado_empleado
            FROM empleado AS e
            INNER JOIN email AS ema ON e.id_empleado = ema.id_empleado
            INNER JOIN contacto AS co ON e.id_contacto = co.id_contacto
            INNER JOIN cargo AS ca ON e.id_cargo = ca.id_cargo
            WHERE ema.email = ?;
            """;

        try (Connection con = connection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            // Configurar el parámetro en la consulta
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                // Recorrer los resultados y mapearlos
                while (rs.next()) {
                    Map<String, Object> Empleado = new HashMap<>();

                    Empleado.put("id_empleado", rs.getInt("id_empleado"));
                    Empleado.put("nombre_empleado", rs.getString("nombre_empleado"));
                    Empleado.put("apellido_empleado", rs.getString("apellido_empleado"));
                    Empleado.put("telefono_1", rs.getString("telefono_1"));
                    Empleado.put("direccion", rs.getString("direccion"));
                    Empleado.put("DUI_empleado", rs.getString("DUI_empleado"));
                    Empleado.put("email", rs.getString("email"));
                    Empleado.put("nombre_cargo", rs.getString("nombre_cargo"));// Agregado
                    Empleado.put("estado_empleado", rs.getInt("estado_empleado"));

                    lista.add(Empleado);
                }
            }

            return lista;

        } catch (SQLException e) {
            alert.showErrorAlert("ERROR", null, "Error al obtener datos del Empleado: " + e.getMessage());
            return lista; // Retornar lista vacía en caso de error
        }
    }

    public void UPDATE_Empleado(empleado empleado, int id) {
        String sql = """
        UPDATE empleado
        SET nombre_empleado = ?,
            apellido_empleado = ?,
            DUI_empleado = ?,
            id_cargo = ?,
            estado_empleado = ?
        WHERE id_empleado = ?""";

        try (Connection con = connection.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            // Validación básica
            if (empleado == null) {
                throw new IllegalArgumentException("El empleado no puede ser nulo");
            }

            pstm.setString(1, empleado.getNombre_empleado());
            pstm.setString(2, empleado.getApellido_empleado());
            pstm.setString(3, empleado.getDUI_empleado());
            pstm.setInt(4, empleado.getId_cargo());
            pstm.setInt(5, empleado.getEstado_empleado());
            pstm.setInt(6, id);

            int filasAfectadas = pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el empleado con ID: " + id, e);
        }
    }

    public static ObservableList<Map> Obtener_idsCorrespondientes_Empleados(String email) {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String query = """
        SELECT
            c.id_contacto,
            emp.id_empleado,
            e.id_email
        FROM contacto AS c
        INNER JOIN empleado AS emp ON c.id_contacto = c.id_contacto
        INNER JOIN email AS e ON emp.id_empleado = e.id_empleado
        WHERE e.email = ?;
    """;

        try (Connection con = connection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            // Configurar el parámetro en la consulta
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                // Recorrer los resultados y mapearlos
                while (rs.next()) {
                    Map<String, Object> Empleado = new HashMap<>();

                    Empleado.put("id_contacto", rs.getInt("id_contacto"));
                    Empleado.put("id_empleado", rs.getInt("id_empleado"));
                    Empleado.put("id_email", rs.getInt("id_email"));

                    lista.add(Empleado);
                }
            }
            return lista;
        } catch (SQLException e) {
            alert.showErrorAlert("", null, "Error al obtener ids del Empleado: " + e.getMessage());
        }

        return null;
    }

}
