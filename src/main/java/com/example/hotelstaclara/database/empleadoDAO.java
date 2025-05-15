package com.example.hotelstaclara.database;

import com.example.hotelstaclara.Recursos.MesajesAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class empleadoDAO {
    MesajesAlert alert=new MesajesAlert();


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

}
