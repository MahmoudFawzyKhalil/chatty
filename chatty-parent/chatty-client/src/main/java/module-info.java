module chatty.client {
    // RMI
    requires java.rmi;
    requires  chatty.commons;

    requires org.slf4j;

    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires validatorfx;

    // ChatBot
    requires Ab;

    // Utils
    // MapStruct
    requires org.mapstruct;
    exports gov.iti.jets.presentation.models.mappers to org.mapstruct;

    // Hibernate Validator
    requires jakarta.validation;
    requires org.hibernate.validator;
    requires org.hibernate.validator.cdi;
    requires javafx.media;


    // Exports, Opens
    exports gov.iti.jets;
    exports gov.iti.jets.presentation.models;
    opens gov.iti.jets.presentation.controllers to javafx.fxml;
    opens gov.iti.jets.presentation.customcontrols to javafx.fxml;
}
