module com.example.hotelstaclara {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hotelstaclara to javafx.fxml;
    exports com.example.hotelstaclara;
}