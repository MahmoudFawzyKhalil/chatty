package gov.iti.jets.presentation.customcontrols;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InvitationBubble extends HBox implements Initializable {
    @FXML
    private Label friendNameLabel;

    @FXML
    private Label friendPhoneNumberLabel;

    public InvitationBubble(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/invitations/InvitationBubble.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onAcceptButtonAction(ActionEvent event) {

    }

    @FXML
    void onRefuseButtonAction(ActionEvent event) {

    }
}
