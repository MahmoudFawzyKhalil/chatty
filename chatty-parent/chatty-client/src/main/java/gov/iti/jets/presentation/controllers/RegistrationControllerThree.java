package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationControllerThree  implements Initializable{

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private UserModel userModel;

    @FXML
    private Button finishButton;

    @FXML
    private Circle profilePictureCircle;

    @FXML
    private Hyperlink uploadPictureHyperLink;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        TODO
//         bindProfilePicCircle();
    }

    private void bindProfilePicCircle() {
        profilePictureCircle.setFill( new ImagePattern( userModel.getProfilePicture() ) );
        userModel.profilePictureProperty().addListener( e -> {
            profilePictureCircle.setFill( new ImagePattern( userModel.getProfilePicture() ));
        } );
    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {

    }

    @FXML
    void onFinishButtonAction(ActionEvent event) {
        stageCoordinator.switchToLoginScene();
    }
}
