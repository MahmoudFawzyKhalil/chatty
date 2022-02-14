package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.customcontrols.AddContactTextField;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddContactController implements Initializable {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private ModelFactory modelFactory = ModelFactory.getInstance();

    private UserModel userModel = modelFactory.getUserModel();

    @FXML
    private VBox contactsVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactsVBox.getChildren().add(new AddContactTextField());
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {

    }

    @FXML
    void onCancelHyperLinkClick(ActionEvent event) {
        stageCoordinator.closeAddContactStage();
    }


}
