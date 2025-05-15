package com.example.hotelstaclara.database;

import com.example.hotelstaclara.Alert.Alert;
import com.example.hotelstaclara.Recursos.MesajesAlert;
import com.example.hotelstaclara.model.contacto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



public class ContactoDAO {

    MesajesAlert alert=new MesajesAlert();

    public void INSERT(contacto contacto) throws SQLException {
        Connection con = connection.getConnection();

        String query = """
                INSERT INTO contacto (telefono_1, direccion)
                VALUES (?,?)
                """;
        if(con != null) {
            try (PreparedStatement ps = con.prepareStatement(query);) {
                ps.setString(1, contacto.getTelefono_1());
                ps.setString(2, contacto.getDireccion());
                ps.execute();
            }catch (SQLException e){
                alert.showErrorAlert("No se pudo ingresar el contacto",null,"Ah ocurrido un error al insertar el contacto" + e.getMessage());
            }
        }
    }

    public ObservableList<Map> get_Contacto(){
        ObservableList<Map> lista = FXCollections.observableArrayList();
        Connection con = connection.getConnection();

        String sql = "select * from contacto";

        if(con != null){
            try(PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

                while(rs.next()){
                    Map m = new HashMap();
                    m.put("id_contacto", rs.getInt("id_contacto"));
                    m.put("telefono_1", rs.getString("telefono_1"));
                    m.put("direccion", rs.getString("direccion"));
                    lista.add(m);
                }
                return lista;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }

    public int Obtener_ultimo_contacto() {
        Connection con = connection.getConnection();
        String sql = "SELECT MAX(id_contacto) AS max_contacto FROM contacto"; // Usamos alias para simplificar
        if (con != null) {
            try (PreparedStatement pstm = con.prepareStatement(sql);
                 ResultSet rs = pstm.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("max_contacto"); // Usamos el alias de la columna
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al obtener el último contacto", e);
            }
        }
        return -1;
    }

    public void UPDATE_CONTACTO(String telefono, String direccion,int id) throws SQLException {

        Connection con = connection.getConnection();
        String sql = """
                UPDATE contacto
                SET telefono_1 = ?, 
                direccion = ?
                WHERE id_contacto = ?;
                """;

        if(con != null) {
            try (PreparedStatement pstm = con.prepareStatement(sql);) {
                pstm.setString(1, telefono);
                pstm.setString(2, direccion);
                pstm.setInt(3, id);
                pstm.execute();
            }catch (SQLException e){
                throw new RuntimeException("Error al actualizar el contacto", e);
            }

        }
    }
}
