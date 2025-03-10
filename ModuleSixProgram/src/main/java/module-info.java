module com.example.modulesixprogram {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.modulesixprogram to javafx.fxml;
    exports com.example.modulesixprogram;
}