package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.UpdateProfileDto;
import gov.iti.jets.presentation.erros.ErrorMessages;
import gov.iti.jets.presentation.models.UpdateProfileModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.models.mappers.UpdateProfileMapper;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.UiValidator;
import gov.iti.jets.services.UpdateProfileDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import net.synedra.validatorfx.Validator;

import java.io.File;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class UpdateProfileController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final UpdateProfileDao updateProfileDao = daoFactory.getUpdateProfileDao();
    private final UserModel userModel = ModelFactory.getInstance().getUserModel();
    private UpdateProfileModel updateProfileModel = ModelFactory.getInstance().getUpdateProfileModel();
    private Validator validator = UiValidator.getInstance().createValidator();


    private FileChooser fileChooser = new FileChooser();

    @FXML
    private TextField bioTextField;


    @FXML
    private TextField nameTextField;

    @FXML
    private Circle profilePictureCircle;

    @FXML
    private Button updateButton;

    @FXML
    private Hyperlink uploadPictureHyperLink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameTextField.textProperty().bindBidirectional(updateProfileModel.displayNameProperty());
        bioTextField.textProperty().bindBidirectional(updateProfileModel.bioProperty());
        addListenerProfilePictureCircle();
        bindProfilePicCircle();
        validateNameTextField();
        validateBioTextField();
        addEnableButtonValidationListener();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpeg", "*.jpg", "*.png", "*.bmp")
        );

    }

    private void addListenerProfilePictureCircle() {
        profilePictureCircle.setFill(new ImagePattern(updateProfileModel.getProfilePicture()));
        updateProfileModel.profilePictureProperty().addListener(e -> {
            profilePictureCircle.setFill(new ImagePattern(updateProfileModel.getProfilePicture()));
        });
    }


    @FXML
    void onUpdateButtonAction(ActionEvent event) {
        try {
            UpdateProfileDto updateProfileDto = UpdateProfileMapper.INSTANCE.updateProfileModelToDto(updateProfileModel);
            if (updateProfileDao.update(updateProfileDto)) {
                updateProfileModel.updateUserModel();
                stageCoordinator.showMessageNotification("Success", "Updated Successfully");
            } else {
                stageCoordinator.showErrorNotification(ErrorMessages.FAILED_Update);
            }

        } catch (NotBoundException | RemoteException e) {
            stageCoordinator.showErrorNotification(ErrorMessages.FAILED_TO_CONNECT);
        }
    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.getPath());
            updateProfileModel.setProfilePicture(image);
        }
    }

    private void bindProfilePicCircle() {
        profilePictureCircle.setFill(new ImagePattern(updateProfileModel.getProfilePicture()));
        updateProfileModel.profilePictureProperty().addListener(e -> {
            profilePictureCircle.setFill(new ImagePattern(updateProfileModel.getProfilePicture()));
        });
    }

    private void validateNameTextField() {
        validator.createCheck()
                .dependsOn("userName", nameTextField.textProperty())
                .dependsOn("bio",bioTextField.textProperty())
                .withMethod(c -> {
                    String userName = c.get("userName");
                    if (!UiValidator.USER_NAME_PATTERN.matcher(userName).matches() || userName.length() > 12 || userName.length() < 3) {
                        c.error("Please enter a valid name between 3 and 12 characters long.");
                        updateButton.setDisable(true);
                    }
                    if (!updateProfileModel.isChanged()) {
                        c.error("No change");
                        updateButton.setDisable(true);
                    }
                })
                .decorates(nameTextField)
                .immediate();
    }



    private void validateBioTextField() {
        validator.createCheck()
                .dependsOn("bio", bioTextField.textProperty())
                .dependsOn("userName", nameTextField.textProperty())
                .withMethod(c -> {
                    String bio = c.get("bio");
                    if (!updateProfileModel.isChanged()) {
                        c.error("No change");
                        updateButton.setDisable(true);
                    }
                })
                .decorates(bioTextField)
                .immediate();
    }


    private void addEnableButtonValidationListener() {
        validator.containsErrorsProperty().addListener(e -> {
            if (!validator.containsErrors()) {
                updateButton.setDisable(false);
            }
        });
    }

}

