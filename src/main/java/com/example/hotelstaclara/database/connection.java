package com.example.hotelstaclara.database;

import java.sql.Connection; // Usa java.sql.Connection
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {

    private static final String URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USER = "roott";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver de MySQL
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
        return connection;
    }

}

