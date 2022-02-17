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
    requires logback.core;
    requires logback.classic;

    // MapStruct
    requires org.mapstruct;
    requires org.slf4j;
    exports gov.iti.jets.repository.util.mappers to org.mapstruct;
    exports gov.iti.jets.repository.entities to org.mapstruct;

    // Hibernate Validator
    requires jakarta.validation;
    requires org.hibernate.validator;
    requires org.hibernate.validator.cdi;

    // Exports, Opens
    exports gov.iti.jets;

    opens gov.iti.jets.presentation.controllers to javafx.fxml;
    opens gov.iti.jets.presentation.customcontrols to javafx.fxml;
}
