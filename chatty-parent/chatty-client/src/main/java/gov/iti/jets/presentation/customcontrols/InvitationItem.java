package gov.iti.jets.presentation.customcontrols;

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

    public InvitationItem(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/invitations/InvitationItem.fxml"));
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
}
