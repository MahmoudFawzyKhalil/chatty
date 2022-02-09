package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.LoginService;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

public class LoginController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final LoginService loginService = serviceFactory.getLoginService();

    private UserModel userModel;


    @FXML
    private Hyperlink createOneHyperLink;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onCreateOneHyperLinkAction(ActionEvent event) {

    }

    @FXML
    void onLoginButtonAction(ActionEvent event) {

    }


}
