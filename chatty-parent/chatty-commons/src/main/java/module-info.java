module chatty.commons {
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    // Database
    requires java.sql;

    // Utils
    requires org.mapstruct;

    // Exports, Opens
    exports gov.iti.jets;
}
