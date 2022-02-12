module chatty.server {
    // RMI
    requires java.rmi;
    requires  chatty.commons;

    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    // Database
    requires java.sql;
    requires com.zaxxer.hikari;
    requires mysql.connector.java;

    // Utils
    requires org.mapstruct;

    // Exports, Opens
    exports gov.iti.jets;

    opens gov.iti.jets.presentation.controllers to javafx.fxml;
    opens gov.iti.jets.presentation.customcontrols to javafx.fxml;
}
