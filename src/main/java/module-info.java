module com.example.hotelstaclara {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.hotelstaclara.model to javafx.base;

    opens com.example.hotelstaclara to javafx.fxml;
    opens com.example.hotelstaclara.controllers.formsAdminControllers to javafx.fxml;
    opens com.example.hotelstaclara.controllers.formsUserControlllers to javafx.fxml;
    exports com.example.hotelstaclara;
    exports com.example.hotelstaclara.controllers;
    opens com.example.hotelstaclara.controllers to javafx.fxml;
    exports com.example.hotelstaclara.controllers.AdminController;
    opens com.example.hotelstaclara.controllers.AdminController to javafx.fxml;
    exports com.example.hotelstaclara.controllers.UserControllers;
    opens com.example.hotelstaclara.controllers.UserControllers to javafx.fxml;
}