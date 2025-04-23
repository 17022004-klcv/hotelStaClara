package com.example.hotelstaclara.database;

import javafx.collections.ObservableList;
import java.util.Map;

public class empleadoDAO {

public ObservableList<Map> getempleado(){
    String sql = """
            SELECT
                em.nombre_empleado,
                em.apellido_empleado,
                co.telefono_1 AS telefono,
                co.direccion,
                em.DUI_empleado,
                e.email,
                ca.nombre_cargo,
                em.estado_empleado
            From empleado AS em
            INNER JOIN cargo AS ca ON em.id_cargo = ca.id_cargo
            INNER JOIN email AS e ON em.id_empleado = e.id_empleado
            INNER JOIN contacto AS co ON em.id_contacto = co.id_contacto
            ;
            """;

//    try(con.getConnection())
    return null;
}

}
