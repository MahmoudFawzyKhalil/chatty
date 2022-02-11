package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable{

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private UserModel userModel;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private Button firstNextButton;

    @FXML
    private Hyperlink loginHyperLink;

    @FXML
    private TextField nameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField bioTextField;

    @FXML
    private ComboBox<?> countryComboBox;

    @FXML
    private ComboBox<?> dayComboBox;

    @FXML
    private TextField emailTextField;

    @FXML
    private ComboBox<?> genderComboBox;

    @FXML
    private ComboBox<?> monthComboBox;

    @FXML
    private Button previousButton;

    @FXML
    private Button secondNextButton;

    @FXML
    private ComboBox<?> yearComboBox;

    @FXML
    private Button finishButton;

    @FXML
    private Circle profilePictureCircle;

    @FXML
    private Hyperlink uploadPictureHyperLink;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onFirstNextButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneTwo();
    }

    @FXML
    void onLoginHyperLinkAction(ActionEvent event) {
        stageCoordinator.switchToLoginScene();
    }

    @FXML
    void onPreviousButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneOne();
    }

    @FXML
    void onSecondNextButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneThree();
    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {

    }

    @FXML
    void onFinishButtonAction(ActionEvent event) {
        stageCoordinator.switchToLoginScene();
    }
}
