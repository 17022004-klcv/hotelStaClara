package com.example.hotelstaclara.database;

import com.example.hotelstaclara.model.contacto;
import com.example.hotelstaclara.model.email;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.hotelstaclara.model.cliente;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

import static com.example.hotelstaclara.database.connection.getConnection;

public class clienteDAO {

    //Obtener cliente para mostrar en la tabla
    public ObservableList<Map> getCliente() throws SQLException {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "select \n" +
                "c.nombre_cliente, c.apellido_cliente, ct.telefono_1, ct.telefono_2, ct.direccion, c.DUI_cliente, e.email, c.estado_cliente\n" +
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
                cliente.put("ct.telefono_2", rs.getString("ct.telefono_2"));
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

    public void insertarCliente(contacto ct, cliente c, email e ){

        // 1. Insertar contacto
        String sqlContacto = "INSERT INTO contacto (telefono_1, telefono_2, direccion) VALUES (?, ?, ?)";

        // 2. Insertar cliente
        String sqlCliente = "INSERT INTO cliente (nombre_cliente, apellido_cliente, DUI_cliente, id_contacto, estado_cliente) VALUES (?, ?, ?, ?, ?)";

        // 3. Insertar email
        String sqlEmail = "INSERT INTO email (email, id_cliente, id_empleado) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement psContacto = con.prepareStatement(sqlContacto, Statement.RETURN_GENERATED_KEYS)) {

            // Insertar contacto
            psContacto.setString(1, ct.getTelefono_1());
            psContacto.setString(2, ct.getTelefono_2());
            psContacto.setString(3, ct.getDireccion());
            psContacto.executeUpdate();

            int idContacto = 0;
            try (ResultSet rsContacto = psContacto.getGeneratedKeys()) {
                if (rsContacto.next()) {
                    idContacto = rsContacto.getInt(1);
                }
            }

            try (PreparedStatement psCliente = con.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS)) {
                psCliente.setString(1, c.getNombre_cliente());
                psCliente.setString(2, c.getApellido_cliente());
                psCliente.setString(3, c.getDUI_cliente());
                psCliente.setInt(4, idContacto);
                psCliente.setInt(5, c.getEstado_cliente());
                psCliente.executeUpdate();

                int idCliente = 0;
                try (ResultSet rsCliente = psCliente.getGeneratedKeys()) {
                    if (rsCliente.next()) {
                        idCliente = rsCliente.getInt(1);
                    }
                }

                try (PreparedStatement psEmail = con.prepareStatement(sqlEmail)) {
                    psEmail.setString(1, e.getEmail());
                    psEmail.setInt(2, idCliente);
                    psEmail.setInt(3, e.getId_empleado());
                    psEmail.executeUpdate();
                }
            }

            System.out.println("Cliente insertado correctamente.");
            JOptionPane.showMessageDialog(null, "Cliente insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void actualizarCliente(contacto ct, cliente c, email e) {

        String sqlBuscarIds = "SELECT cliente.id_cliente, cliente.id_contacto FROM cliente WHERE DUI_cliente = ?";
        String sqlActualizarContacto = "UPDATE contacto SET telefono_1 = ?, telefono_2 = ?, direccion = ? WHERE id_contacto = ?";
        String sqlActualizarCliente = "UPDATE cliente SET nombre_cliente = ?, apellido_cliente = ?, estado_cliente = ? WHERE id_cliente = ?";
        String sqlActualizarEmail = "UPDATE email SET email = ?, id_empleado = ? WHERE id_cliente = ?";

        try (Connection con = getConnection();
             PreparedStatement psBuscar = con.prepareStatement(sqlBuscarIds)) {

            // Buscar IDs por DUI
            psBuscar.setString(1, c.getDUI_cliente());

            int idCliente = -1;
            int idContacto = -1;

            try (ResultSet rs = psBuscar.executeQuery()) {
                if (rs.next()) {
                    idCliente = rs.getInt("id_cliente");
                    idContacto = rs.getInt("id_contacto");
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado con DUI: " + c.getDUI_cliente(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Actualizar contacto
            try (PreparedStatement psContacto = con.prepareStatement(sqlActualizarContacto)) {
                psContacto.setString(1, ct.getTelefono_1());
                psContacto.setString(2, ct.getTelefono_2());
                psContacto.setString(3, ct.getDireccion());
                psContacto.setInt(4, idContacto);
                psContacto.executeUpdate();
            }

            // Actualizar cliente
            try (PreparedStatement psCliente = con.prepareStatement(sqlActualizarCliente)) {
                psCliente.setString(1, c.getNombre_cliente());
                psCliente.setString(2, c.getApellido_cliente());
                psCliente.setInt(3, c.getEstado_cliente());
                psCliente.setInt(4, idCliente);
                psCliente.executeUpdate();
            }

            // Actualizar email
            try (PreparedStatement psEmail = con.prepareStatement(sqlActualizarEmail)) {
                psEmail.setString(1, e.getEmail());
                psEmail.setInt(2, e.getId_empleado());
                psEmail.setInt(3, idCliente);
                psEmail.executeUpdate();
            }

            System.out.println("Cliente actualizado correctamente.");
            JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void inactivarClientePorDUI(String dui) {
        String sql = "UPDATE cliente SET estado_cliente = 0 WHERE DUI_cliente = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dui);
            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Cliente inactivado correctamente.");
                JOptionPane.showMessageDialog(null, "Cliente inactivado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("No se encontró un cliente con el DUI especificado.");
                JOptionPane.showMessageDialog(null, "No se encontró un cliente con el DUI: " + dui, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al inactivar cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
