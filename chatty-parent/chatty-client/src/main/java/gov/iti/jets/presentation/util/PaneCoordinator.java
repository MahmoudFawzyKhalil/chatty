package gov.iti.jets.presentation.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PaneCoordinator {
    private static final PaneCoordinator paneCoordinator = new PaneCoordinator();

    private final Map<String, Pane> paneMap = new HashMap<>();
    private BorderPane mainSceneBorderPane;

    private PaneCoordinator() {
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

        mainSceneBorderPane.setCenter(invitationPane);
    }

    public void clearPaneMap() {
        paneMap.clear();
    }

    public void switchToChatPane() {
        Pane chatPane = paneMap.get("chatPane");
        if (chatPane == null) {
            try {
                chatPane = FXMLLoader.load(getClass().getResource("/views/chat/ChatView.fxml"));
                paneMap.put("chatPane", chatPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mainSceneBorderPane.setCenter(chatPane);
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

        mainSceneBorderPane.setCenter(updateProfilePane);
    }


}
