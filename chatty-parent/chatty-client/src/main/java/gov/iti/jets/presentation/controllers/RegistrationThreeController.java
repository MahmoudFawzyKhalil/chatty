package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.RegisterDto;
import gov.iti.jets.presentation.erros.ErrorMessages;
import gov.iti.jets.presentation.models.RegisterModel;
import gov.iti.jets.presentation.models.mappers.RegisterMapper;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.RegisterDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegistrationThreeController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final RegisterModel registerModel = modelFactory.getRegisterModel();
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final RegisterDao registerDao = daoFactory.getRegisterDao();
    private FileChooser fileChooser = new FileChooser();

    @FXML
    private Circle profilePictureCircle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindProfilePicCircle();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpeg", "*.jpg", "*.png", "*.bmp")
        );


    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            double fileLen = selectedFile.length() / (double) (1024 * 1024);
            if (fileLen > 2) {
                stageCoordinator.showErrorNotification(ErrorMessages.IMAGE_LENGTH);
                return;
            }
            Image selectedImage = new Image(selectedFile.getPath());
            registerModel.setProfilePicture(selectedImage);
        }
    }

    @FXML
    void onFinishButtonAction(ActionEvent event) {
        if (registered()) {
            registerModel.clear();
            stageCoordinator.switchToLoginScene();
        }
    }

    boolean registered() {
        RegisterDto registerDto = RegisterMapper.INSTANCE.registerModelToDto(registerModel);
        try {
            if (registerDao.register(registerDto)) {
                stageCoordinator.showMessageNotification("Success", "Created Successfully");
                return true;
            } else {
                stageCoordinator.showErrorNotification(ErrorMessages.FAILED_REGISTER);
            }

        } catch (ConnectException c) {
            stageCoordinator.showErrorNotification("Failed to connect to server. Please try again later.");
            ModelFactory.getInstance().clearUserModel();
            modelFactory.clearUserModel();
            stageCoordinator.switchToConnectToServer();
        }catch (NotBoundException | RemoteException e) {
            stageCoordinator.showErrorNotification(ErrorMessages.FAILED_TO_CONNECT);
        }
        return false;
    }

    @FXML
    void onPreviousButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneTwo();
    }

    private void bindProfilePicCircle() {
        profilePictureCircle.setFill(new ImagePattern(registerModel.getProfilePicture()));
        registerModel.profilePictureProperty().addListener(e -> {
            profilePictureCircle.setFill(new ImagePattern(registerModel.getProfilePicture()));
        });
    }
}
