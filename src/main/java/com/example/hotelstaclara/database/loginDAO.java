package com.example.hotelstaclara.database;

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
                WHERE lo.usuario = ? and lo.contraseña = ?
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

    public boolean verificarCredenciales(String nombreUsuario, String contrasena) {
        String sql = "SELECT contraseña FROM login WHERE usuario = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String contrasenaAlmacenada = rs.getString("contraseña");
                    return contrasena.equals(contrasenaAlmacenada);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


