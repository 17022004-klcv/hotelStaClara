package com.example.hotelstaclara.database;

import com.example.hotelstaclara.Recursos.MesajesAlert;

import java.sql.*;

public class emailDAO {

    MesajesAlert alert=new MesajesAlert();

    public void Insert_Email_Empleado(String email, int id_empleado) throws SQLException {
        Connection con = connection.getConnection();

        String query = """
            INSERT INTO email (email, id_empleado) VALUES (?, ?);
            """;

        if (con != null) {
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, email);
                ps.setInt(2, id_empleado);
                ps.execute();

                alert.showInfoAlert("Éxito", null, "El empleado se agregó correctamente");
            } catch (SQLIntegrityConstraintViolationException e) {
                alert.showErrorAlert("Email duplicado", null, "Ya existe un registro con ese correo electrónico.");
            } catch (SQLException e) {
                alert.showErrorAlert("Error al ingresar el Email", null,
                        "Ha ocurrido un error al intentar ingresar el Email del empleado: " + e.getMessage());
            }
        }
    }


    public static int ObtenerUltimoid_empleado() throws SQLException {
        String sql = """
        SELECT MAX(id_empleado) AS empleado 
        FROM empleado;
        """;

        try (Connection con = connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) { // Cambiado a if porque solo hay una fila
                return rs.getInt("empleado"); // Accedemos por alias, más claro que usar índice
            }

        } catch (SQLException e) {
            throw new SQLException("Error al obtener el último empleados: " + e.getMessage(), e);
        }

        return -1; // Si no hay registros, devolvemos -1
    }

    public int ObtenerUltimoid_cliente() throws SQLException {
        String sql = """
        SELECT MAX(id_cliente) AS cliente 
        FROM cliente;
        """;

        try (Connection con = connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) { // Cambiado a if porque solo hay una fila
                return rs.getInt("cliente"); // Accedemos por alias, más claro que usar índice
            }

        } catch (SQLException e) {
            throw new SQLException("Error al obtener el último id_cliente: " + e.getMessage(), e);
        }

        return -1; // Si no hay registros, devolvemos -1
    }

    //Update Email
    public void UPDATE_Email(String correo,int id){
        Connection con = connection.getConnection();

        String sql = """
                UPDATE email
                SET email = ?
                WHERE id_email = ?;
                """;

        if(con != null) {
            try (PreparedStatement pstm = con.prepareStatement(sql);) {
                pstm.setString(1, correo);
                pstm.setInt(2, id);
                pstm.execute();
            }catch (SQLException e){
                throw new RuntimeException("Error al actualizar el contacto", e);
            }

        }
    }
}
