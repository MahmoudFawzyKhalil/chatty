package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.UiValidator;
import gov.iti.jets.services.LoginDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final LoginDao loginDao = daoFactory.getLoginService();

    private UserModel userModel;

    private Validator validator = UiValidator.getInstance().createValidator();

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
        validatePhoneNumberTextField();
        validatePasswordTextField();
    }

    private void validatePasswordTextField() {
        validator.createCheck()
                .dependsOn("password", passwordTextField.textProperty())
                .withMethod(c -> {
                    String password = c.get("password");
                    if (password.length() < 8 || password.length() > 20) {
                        c.error("Please enter a valid password between 8 and 20 characters long.");
                        loginButton.setDisable(true);
                    } else {
                        if (!validator.containsErrors()) {
                            loginButton.setDisable(false);
                        }
                    }
                })
                .decorates(passwordTextField)
                .immediate();
    }

    private void validatePhoneNumberTextField() {
        validator.createCheck()
                .dependsOn("phoneNumber", phoneNumberTextField.textProperty())
                .withMethod(c -> {
                    String phoneNumber = c.get("phoneNumber");
                    if (!UiValidator.PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
                        c.error("Please enter a valid 11 digit phone number.");
                        loginButton.setDisable(true);
                    } else {
                        if (!validator.containsErrors()) {
                            loginButton.setDisable(false);
                        }
                    }
                })
                .decorates(phoneNumberTextField)
                .immediate();
    }

    @FXML
    void onCreateAccountHyperLinkAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneOne();
    }

    @FXML
    void onLoginButtonAction(ActionEvent event) {
        LoginDto loginDto = new LoginDto(phoneNumberTextField.getText(), passwordTextField.getText());
        try {
            boolean isAuthenticated = loginDao.isAuthenticated(loginDto);
            if(isAuthenticated){
                stageCoordinator.switchToMainScene();
            }else{
                //TODO
                //wrong
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
