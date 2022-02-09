module chatty.client {
    // RMI
    requires java.rmi;
    
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    // Utils
    requires org.mapstruct;

    // Exports, Opens
    exports gov.iti.jets;
    opens gov.iti.jets.presentation.controllers to javafx.fxml;
    opens gov.iti.jets.presentation.customcontrols to javafx.fxml;
}
