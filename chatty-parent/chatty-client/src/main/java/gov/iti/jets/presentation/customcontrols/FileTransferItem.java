package gov.iti.jets.presentation.customcontrols;

import gov.iti.jets.presentation.models.FileModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FileTransferItem extends VBox implements Initializable {

    @FXML
    private Label fileNameLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private ProgressBar fileProgressBar;

    FileModel fileModel;

    public FileTransferItem(FileModel fileTransferModel){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/filetransfer/FileTransferItem.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        this.fileModel = fileTransferModel;
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileNameLabel.textProperty().bind(fileModel.fileNameProperty());
        fileProgressBar.progressProperty().bind(fileModel.fileTransferProgressProperty());
    }

    @FXML
    void onCancelButton(ActionEvent event) {

    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
