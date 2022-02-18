package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.AddGroupChatDto;
import gov.iti.jets.presentation.erros.ErrorMessages;
import gov.iti.jets.presentation.models.GroupChatModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.models.mappers.GroupChatMapper;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.UiValidator;
import gov.iti.jets.services.AddGroupChatDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AddGroupChatTwoController implements Initializable {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private ModelFactory modelFactory = ModelFactory.getInstance();
    private UserModel userModel = modelFactory.getUserModel();
    private GroupChatModel createGroupChatModel = modelFactory.getCreateGroupChatModel();

    private AddGroupChatDao addGroupChatDao = DaoFactory.getInstance().getAddGroupChatDao();
    private Validator validator = UiValidator.getInstance().createValidator();

    @FXML
    private TextField groupNameTextField;

    @FXML
    private Circle groupPictureCircle;

    @FXML
    private Button createButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createGroupChatModel.groupChatNameProperty().bind(groupNameTextField.textProperty());
        addGroupPicCircleListener();
        validateGroupNameTextField();
        addEnableButtonValidationListener();
        System.out.println(createGroupChatModel.getGroupMembersList().size());
    }

    private void validateGroupNameTextField() {
        validator.createCheck()
                .dependsOn("groupChatName", groupNameTextField.textProperty())
                .withMethod(c -> {
                    String groupChatName = c.get("groupChatName");
                    if (!UiValidator.GROUP_CHAT_NAME_PATTERN.matcher(groupChatName).matches() || groupChatName.length() > 12 || groupChatName.length() < 3) {
                        c.error("Please enter a valid group name between 3 and 12 characters long.");
                        createButton.setDisable(true);
                    }
                })
                .decorates(groupNameTextField)
                .immediate();
    }

    private void addGroupPicCircleListener() {
        createGroupChatModel.groupChatPictureProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null)
                groupPictureCircle.setFill(new ImagePattern(newVal));
        });
    }

    private void addEnableButtonValidationListener() {
        validator.containsErrorsProperty().addListener(e -> {
            if (!validator.containsErrors()) {
                createButton.setDisable(false);
            }
        });
    }

    @FXML
    void onCancelHyperLinkAction(ActionEvent event) {
        close();
    }


    @FXML
    void onCreateButtonAction(ActionEvent event) {
        AddGroupChatDto addGroupChatDto = GroupChatMapper.INSTANCE.modelToAddGroupChatDto(createGroupChatModel);
        if (create(addGroupChatDto)) {
            stageCoordinator.showMessageNotification("Success", "Created successfully");
            close();
        }

    }

    private void close() {
        userModel.clearSelectedContacts();
        createGroupChatModel.clear();
        stageCoordinator.closeAddGroupChatStage();
    }

    private boolean create(AddGroupChatDto addGroupChatDto) {
        try {
            return addGroupChatDao.addGroup(addGroupChatDto);
        } catch (NotBoundException | RemoteException e) {
            stageCoordinator.showErrorNotification(ErrorMessages.FAILED_TO_CONNECT);
        }
        return false;
    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {

    }


}