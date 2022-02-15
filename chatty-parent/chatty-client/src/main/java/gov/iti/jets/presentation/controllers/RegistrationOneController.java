package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationOneController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserModel userModel;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = modelFactory.getUserModel();
        phoneNumberTextField.textProperty().bindBidirectional(userModel.phoneNumberProperty());
        nameTextField.textProperty().bindBidirectional(userModel.displayNameProperty());
    }

    @FXML
    void onNextButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneTwo();
    }

    @FXML
    void onLoginHyperLinkAction(ActionEvent event) {
        stageCoordinator.switchToLoginScene();
    }
}
