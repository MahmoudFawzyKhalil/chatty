package gov.iti.jets.presentation.customcontrols;

import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.util.StatusColors;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactChatMenuItem extends HBox implements Initializable {


    ContactModel contactModel;

    @FXML
    private Label contactChatNameLabel;

    @FXML
    private Circle contactProfilePicCircle;

    @FXML
    private Circle contactStatusCircle;

    public ContactChatMenuItem(ContactModel contactModel) {
        this.contactModel = contactModel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main/ContactChatMenuItem.fxml"));
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
        bindProfilePicCircle();
        bindContactChatNameLabel();
        bindContactStatusCircle();
    }

    private void bindContactChatNameLabel() {
        contactChatNameLabel.textProperty().bind( contactModel.displayNameProperty() );
    }

    private void bindContactStatusCircle() {
        System.err.println("WTF" + contactModel.getCurrentStatus().getUserStatusName());
        contactStatusCircle.setFill( StatusColors.getColorFromStatusName( contactModel.getCurrentStatus().getUserStatusName() ) );
        contactModel.currentStatusProperty().addListener( e -> {
            contactStatusCircle.setFill( StatusColors.getColorFromStatusName( contactModel.getCurrentStatus().getUserStatusName() ) );
        } );
    }


    private void bindProfilePicCircle() {
        contactProfilePicCircle.setFill( new ImagePattern( contactModel.getProfilePicture() ) );
        contactModel.profilePictureProperty().addListener( e -> {
            contactProfilePicCircle.setFill( new ImagePattern( contactModel.getProfilePicture() ) );
        } );
    }
}