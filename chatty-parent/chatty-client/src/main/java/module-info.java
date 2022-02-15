module chatty.client {
    // RMI
    requires java.rmi;
    requires  chatty.commons;

    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    // Utils
    // MapStruct
    requires org.mapstruct;
    exports gov.iti.jets.services.util.mappers to org.mapstruct;

    // Hibernate Validator
    // COULD BE USELESS
    requires jakarta.validation;
    requires org.hibernate.validator;
    requires org.hibernate.validator.cdi;

    // Exports, Opens
    exports gov.iti.jets;
    opens gov.iti.jets.presentation.controllers to javafx.fxml;
    opens gov.iti.jets.presentation.customcontrols to javafx.fxml;
}
