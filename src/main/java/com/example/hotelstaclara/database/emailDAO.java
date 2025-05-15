package com.example.hotelstaclara.database;

import com.example.hotelstaclara.Recursos.MesajesAlert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class emailDAO {

    MesajesAlert alert=new MesajesAlert();

    public void Insert_Email_Empleado(String email, int  id_empleado) throws SQLException {

        Connection con = connection.getConnection();

        String query = """
                INSERT INTO email (email,id_empleado) VALUES (?,?);
                """;

        if(con != null) {

            try(PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, email);
                ps.setInt(2, id_empleado);
                ps.execute();

                alert.showInfoAlert("Exito",null,"El Empleado se agrego correctamente");
            } catch (SQLException e) {
                alert.showErrorAlert("No se pudo ingresar el Email",null,"Ah ocurrido un error al intenter ingresar el Email del empleado" + e.getMessage());
            }
        }
    }

    public void Insert_Email_Cliente(String email, int  id_Cliente) throws SQLException {

        Connection con = connection.getConnection();

        String query = """
                INSERT INTO email (email,id_cliente) VALUES (?,?);
                """;

        if(con != null) {

            try(PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, email);
                ps.setInt(2, id_Cliente);
                ps.execute();

                alert.showInfoAlert("Exito",null,"El cliente se agrego correctamente");
            } catch (SQLException e) {
                alert.showErrorAlert("No se pudo ingresar el Email",null,"Ah ocurrido un error al intenter ingresar el Email del Cliente" + e.getMessage());
            }

        }
    }

    public int ObtenerUltimoid_empleado() throws SQLException {
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
