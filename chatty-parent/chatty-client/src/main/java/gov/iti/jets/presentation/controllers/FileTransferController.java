package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.FileModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.cellfactories.FileTransferItemCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class FileTransferController implements Initializable {

    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private UserModel userModel = modelFactory.getUserModel();

    @FXML
    private ListView<FileModel> fileTransferListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileTransferListView.setCellFactory(new FileTransferItemCellFactory());
        fileTransferListView.itemsProperty().bind(userModel.fileTransferListProperty());
    }
}
