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

    UserModel userModel;
    
    @FXML
    private TextField nameTextField;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = modelFactory.getUserModel();
    }

    @FXML
    void onImageButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File imageFile = fileChooser.showOpenDialog(null);

        if (imageFile == null) return;

        Image image = new Image(imageFile.getAbsolutePath());
        userModel.setUserImage(image);
    }

    @FXML
    void onYallaButtonAction(ActionEvent event) {
        if (nameTextField.getText().isEmpty()) return;

        userModel.setUserName(nameTextField.getText());
        stageCoordinator.switchToChatScene();
        stageCoordinator.setStageResizable(true);
    }
}
