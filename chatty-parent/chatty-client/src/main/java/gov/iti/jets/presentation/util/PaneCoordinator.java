package gov.iti.jets.presentation.util;

import gov.iti.jets.presentation.models.UserModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PaneCoordinator {
    private static final PaneCoordinator paneCoordinator = new PaneCoordinator();
    private final UserModel userModel = ModelFactory.getInstance().getUserModel();
    private final Map<String, Pane> paneMap = new HashMap<>();
    private BorderPane mainSceneBorderPane;

    private PaneCoordinator() {
        loadChatPane();
    }

    public static PaneCoordinator getInstance() {
        return paneCoordinator;
    }

    public void initPane(BorderPane mainSceneBorderPane) {
        this.mainSceneBorderPane = mainSceneBorderPane;
    }

    public void switchToInvitationPane() {
        Pane invitationPane = paneMap.get("invitationPane");
        if (invitationPane == null) {
            try {
                invitationPane = FXMLLoader.load(getClass().getResource("/views/invitations/InvitationView.fxml"));
                paneMap.put("invitationPane", invitationPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userModel.setCurrentlyChattingWith(null);
        mainSceneBorderPane.setCenter(invitationPane);
    }

    public void clearPaneMap() {
        paneMap.clear();
        userModel.setCurrentlyChattingWith(null);
    }

    public void switchToChatPane() {
        Pane chatPane = paneMap.get("chatPane");
        mainSceneBorderPane.setCenter(chatPane);
    }

    private void loadChatPane() {
        Pane chatPane = paneMap.get("chatPane");
        if (chatPane == null) {
            try {
                chatPane = FXMLLoader.load(getClass().getResource("/views/chat/ChatView.fxml"));
                paneMap.put("chatPane", chatPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void switchToUpdateProfilePane() {
        Pane updateProfilePane = paneMap.get("updateProfilePane");
        if (updateProfilePane == null) {
            try {
                updateProfilePane = FXMLLoader.load(getClass().getResource("/views/updateprofile/UpdateProfileView.fxml"));
                paneMap.put("updateProfilePane", updateProfilePane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelFactory.getInstance().getUpdateProfileModel().resetData();
        userModel.setCurrentlyChattingWith(null);
        mainSceneBorderPane.setCenter(updateProfilePane);
    }

    public void switchToFileTransferPane() {
        Pane fileTransferPane = paneMap.get("fileTransferPane");
        if (fileTransferPane == null) {
            try {
                fileTransferPane = FXMLLoader.load(getClass().getResource("/views/filetransfer/FileTransferView.fxml"));
                paneMap.put("fileTransferPane", fileTransferPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userModel.setCurrentlyChattingWith(null);
        mainSceneBorderPane.setCenter(fileTransferPane);
    }

}
