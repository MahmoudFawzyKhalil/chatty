package gov.iti.jets.presentation.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class FileTransferOperationAvailabilityModel {
    private boolean isAvailable ;

    public FileTransferOperationAvailabilityModel() {
        isAvailable = true;
    }

    public FileTransferOperationAvailabilityModel(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
