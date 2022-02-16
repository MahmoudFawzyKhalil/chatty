package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.UiValidator;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationOneController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private Validator validator = UiValidator.getInstance().createValidator();
    private UserModel userModel;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Button registerButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = modelFactory.getUserModel();
        phoneNumberTextField.textProperty().bindBidirectional(userModel.phoneNumberProperty());
        nameTextField.textProperty().bindBidirectional(userModel.displayNameProperty());

        validatePhoneNumberTextField();
        validateNameTextField();
        validatePasswordTextField();
        validateConfirmPasswordTextField();
    }

    @FXML
    void onNextButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneTwo();
    }

    @FXML
    void onLoginHyperLinkAction(ActionEvent event) {
        stageCoordinator.switchToLoginScene();
    }


    private void validatePhoneNumberTextField() {
        validator.createCheck()
                .dependsOn( "phoneNumber", phoneNumberTextField.textProperty() )
                .withMethod( c -> {
                    String phoneNumber = c.get( "phoneNumber" );
                    if (!UiValidator.PHONE_NUMBER_PATTERN.matcher( phoneNumber ).matches()) {
                        c.error( "Please enter a valid 11 digit phone number." );
                        registerButton.setDisable( true );
                    } else {
                        if (!validator.containsErrors()){
                            registerButton.setDisable( false );
                        }
                    }
                } )
                .decorates( phoneNumberTextField )
                .immediate();
    }

    private void validateNameTextField() {
        validator.createCheck()
                .dependsOn( "userName", nameTextField.textProperty() )
                .withMethod( c -> {
                    String userName = c.get( "userName" );
                    if (!UiValidator.USER_NAME_PATTERN.matcher( userName ).matches()) {
                        c.error( "Please enter a valid name between 3 and 12 digit." );
                        registerButton.setDisable( true );
                    } else {
                        if (!validator.containsErrors()){
                            registerButton.setDisable( false );
                        }
                    }
                } )
                .decorates( nameTextField )
                .immediate();
    }

    private void validatePasswordTextField() {
        validator.createCheck()
                .dependsOn( "password", passwordTextField.textProperty() )
                .withMethod( c -> {
                    String password = c.get( "password" );
                    if (password.length() < 8 || password.length() > 20) {
                        c.error( "Please enter a valid password between 8 and 20 characters long." );
                        registerButton.setDisable( true );
                    } else {
                        if (!validator.containsErrors()){
                            registerButton.setDisable( false );
                        }
                    }
                } )
                .decorates( passwordTextField )
                .immediate();
    }

    private void validateConfirmPasswordTextField() {
        validator.createCheck()
                .dependsOn( "password", passwordTextField.textProperty() )
                .dependsOn( "confirmPassword", confirmPasswordTextField.textProperty() )
                .withMethod( c -> {
                    String password = c.get( "password" );
                    String confirmPassword = c.get( "confirmPassword" );
                    if (!confirmPassword.equals(password)) {
                        c.error( "confirmed password must be the same as password" );
                        registerButton.setDisable( true );
                    } else {
                        if (!validator.containsErrors()){
                            registerButton.setDisable( false );
                        }
                    }
                } )
                .decorates( confirmPasswordTextField )
                .immediate();
    }


}
