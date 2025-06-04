package com.example.hotelstaclara.database;

import com.example.hotelstaclara.model.IdEmpleado;
import com.example.hotelstaclara.model.login;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.example.hotelstaclara.database.connection;
import javafx.collections.ObservableMap;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.example.hotelstaclara.database.connection.getConnection;

public class loginDAO {

    public List<login> getUserName(String usuario, String contra) throws SQLException {
        List<login> result=new ArrayList<>();

        Connection con = getConnection();

        String query = """
                SELECT
                	nombre_empleado AS nombre
                FROM empleado AS e
                INNER JOIN cargo AS c ON e.id_cargo = c.id_cargo
                INNER JOIN login AS lo ON e.id_empleado = lo.id_empleado
                WHERE lo.usuario = ? and lo.contraseña = ? and c.nombre_cargo = ?
                ;
                """;

        try(PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, usuario);
            pstmt.setString(2, contra);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                String nombre ;
            }
        }
       return null;
    }

    public String verificarCredenciales(String nombreUsuario, String contrasena) {
        IdEmpleado idEmpleado = new IdEmpleado();
        String sql = """
               SELECT 
                    e.id_empleado,
                    c.nombre_cargo
               FROM login l
               JOIN empleado e ON l.id_empleado = e.id_empleado
               JOIN cargo c ON e.id_cargo = c.id_cargo
               WHERE l.usuario = ? AND l.contraseña = ?;
                """;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    IdEmpleado.setIdEmpleado(rs.getInt("id_empleado"));
                    return rs.getString("nombre_cargo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener datos de la base de datos!");
        }
        return null;
    }


}


