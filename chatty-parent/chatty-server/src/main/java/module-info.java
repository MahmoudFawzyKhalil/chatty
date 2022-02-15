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
    // MapStruct
    requires org.mapstruct;
    exports gov.iti.jets.repository.util.mappers to org.mapstruct;

    // Hibernate Validator
    requires jakarta.validation;
    requires org.hibernate.validator;
    requires org.hibernate.validator.cdi;
    opens gov.iti.jets.services.dtos to org.hibernate.validator;

    // Exports, Opens
    exports gov.iti.jets;

    opens gov.iti.jets.presentation.controllers to javafx.fxml;
    opens gov.iti.jets.presentation.customcontrols to javafx.fxml;
}
