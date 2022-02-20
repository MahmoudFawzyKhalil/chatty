module chatty.commons {
    // RMI
    requires java.rmi;

    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires transitive javafx.swing;

    // Utils
    // Hibernate Validator
    requires jakarta.validation;
    requires org.hibernate.validator;
    requires org.hibernate.validator.cdi;
    opens gov.iti.jets.commons.dtos to org.hibernate.validator;

    // Exports, Opens
    exports gov.iti.jets.commons;
    exports gov.iti.jets.commons.remoteinterfaces;
    exports gov.iti.jets.commons.dtos;
    exports gov.iti.jets.commons.callback;
    exports gov.iti.jets.commons.util.mappers;
    opens gov.iti.jets.commons.util.mappers to org.mapstruct;
}
