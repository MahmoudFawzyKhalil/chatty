package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.customcontrols.AddContactTextField;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddContactController implements Initializable {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private ListView contactsListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        contactsVBox.getChildren().add(new AddContactTextField());
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {

    }

    @FXML
    void onCancelHyperLinkClick(ActionEvent event) {
        stageCoordinator.closeAddContactScene();
    }


}
