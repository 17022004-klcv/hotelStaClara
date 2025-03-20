package com.example.hotelstaclara;

import com.example.hotelstaclara.database.connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
 //       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/hotelstaclara/views/Admin/AdminClientes.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/hotelstaclara/views/Admin/AdminEmpleados.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/hotelstaclara/views/Admin/AdminHabitaciones.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/hotelstaclara/views/Admin/AdminPagos.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/hotelstaclara/views/Admin/AdminReservaciones.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/hotelstaclara/views/login.fxml"));


        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}