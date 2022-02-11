package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.LoginService;
import gov.iti.jets.services.dtos.LoginDto;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final LoginService loginService = serviceFactory.getLoginService();

    private UserModel userModel;


    @FXML
    private Hyperlink createAccountHyperLink;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onCreateAccountHyperLinkAction(ActionEvent event) {
        switchToRegisterOne();
    }

    @FXML
    void onLoginButtonAction(ActionEvent event) {
        switchToMain();
    }

    private void switchToMain() {
        stageCoordinator.switchToMainScene();
    }

    private void switchToRegisterOne() {
        stageCoordinator.switchToRegisterSceneOne();
    }


}
