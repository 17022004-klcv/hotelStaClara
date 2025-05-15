package com.example.hotelstaclara.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.hotelstaclara.model.cliente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.example.hotelstaclara.database.connection.getConnection;

public class clienteDAO {

    //Obtener cliente para mostrar en la tabla
    public ObservableList<Map> getCliente() throws SQLException {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "select \n" +
                "c.nombre_cliente, c.apellido_cliente, ct.telefono_1, ct.direccion, c.DUI_cliente, e.email, c.estado_cliente\n" +
                "from cliente c\n" +
                "inner join contacto ct on c.id_contacto = ct.id_contacto\n" +
                "inner join email e on e.id_email = c.id_cliente;";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> cliente = new HashMap<>();
                cliente.put("c.nombre_cliente", rs.getString("c.nombre_cliente"));
                cliente.put("c.apellido_cliente", rs.getString("c.apellido_cliente"));
                cliente.put("ct.telefono_1", rs.getString("ct.telefono_1"));
                cliente.put("ct.direccion", rs.getString("ct.direccion"));
                cliente.put("c.DUI_cliente", rs.getString("c.DUI_cliente"));
                cliente.put("e.email", rs.getString("e.email"));
                cliente.put("c.estado_cliente", rs.getString("c.estado_cliente"));
                lista.add(cliente);
            }

        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"MISTAKE TO SELECT CLIENTS" + e.getMessage());
            throw e;
        }

        return lista;
    }

}
