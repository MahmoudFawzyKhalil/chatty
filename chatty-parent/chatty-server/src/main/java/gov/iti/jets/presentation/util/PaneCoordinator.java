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



    public void switchToChatPane() {
        Pane chatPane = paneMap.get("chatPane");
        mainSceneBorderPane.setCenter(chatPane);
    }

    private void loadChatPane(){
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




}
