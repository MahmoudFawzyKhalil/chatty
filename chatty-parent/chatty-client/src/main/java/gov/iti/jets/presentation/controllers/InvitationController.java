package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.customcontrols.InvitationItem;
import gov.iti.jets.presentation.models.InvitationModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.cellfactories.InvitationItemCellFactory;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class InvitationController implements Initializable {

    @FXML
    private ListView<InvitationModel> invitationListView;

    @FXML
    private Label numberOfRequestsLabel;

    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        numberOfRequestsLabel.textProperty().bind(userModel.invitationsProperty().sizeProperty().asString());

        invitationListView.setCellFactory(new InvitationItemCellFactory());
        invitationListView.itemsProperty().bind(userModel.invitationsProperty());

    }


}
