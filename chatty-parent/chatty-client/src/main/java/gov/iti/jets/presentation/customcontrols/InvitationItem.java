package gov.iti.jets.presentation.customcontrols;

import gov.iti.jets.presentation.models.InvitationModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InvitationItem extends VBox implements Initializable {


    @FXML
    private Button acceptButton;

    @FXML
    private Label friendNameLabel;

    @FXML
    private Label friendPhoneNumberLabel;

    @FXML
    private Button refuseButton;

    InvitationModel invitationModel;

    public InvitationItem(InvitationModel invitationModel){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/invitations/InvitationItem.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        this.invitationModel = invitationModel;

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        friendNameLabel.textProperty().bind(invitationModel.getContactModel().displayNameProperty());
        friendPhoneNumberLabel.textProperty().bind(invitationModel.getContactModel().phoneNumberProperty());
    }

    public Button getAcceptButton() {
        return acceptButton;
    }

    public Button getRefuseButton() {
        return refuseButton;
    }

}
