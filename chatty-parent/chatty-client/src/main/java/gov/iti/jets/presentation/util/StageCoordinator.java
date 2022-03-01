package gov.iti.jets.presentation.util;

import gov.iti.jets.commons.dtos.AnnouncementDto;
import gov.iti.jets.network.VoiceReceiver;
import gov.iti.jets.network.VoiceSender;
import gov.iti.jets.presentation.customcontrols.ReceivedAnnouncementBubble;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StageCoordinator {
    private static final StageCoordinator stageCoordinator = new StageCoordinator();
    private static final PaneCoordinator paneCoordinator = PaneCoordinator.getInstance();

    private Stage primaryStage;
    private final Map<String, Scene> sceneMap = new HashMap<>();
    private final Map<String, Stage> stageMap = new HashMap<>();

    private StageCoordinator() {
    }

    public static StageCoordinator getInstance() {
        return stageCoordinator;
    }

    public void initStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setWidth( 960 );
        this.primaryStage.setHeight( 530 );
        this.primaryStage.setMinWidth( 960 );
        this.primaryStage.setMinHeight( 530 );
        setAppIconAndTitle();
    }

    private void setAppIconAndTitle() {
        primaryStage.getIcons().add(new Image(StageCoordinator.class.getResource("/images/appIcon.png").toString()));
        primaryStage.setTitle("Chatty");
    }

    public void switchToLoginScene() {
        Scene loginScene = sceneMap.get("loginScene");
        if (loginScene == null) {
            try {
                Pane root = FXMLLoader.load(getClass().getResource("/views/login/LoginView.fxml"));
                loginScene = new Scene(root);
                sceneMap.put("loginScene", loginScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setWidth(primaryStage.getWidth() + 1);
        primaryStage.setHeight(primaryStage.getHeight() + 1);
        setSceneStyleSheets(loginScene);
        primaryStage.setScene(loginScene);
        primaryStage.setWidth(primaryStage.getWidth() - 1);
        primaryStage.setHeight(primaryStage.getHeight() - 1);
    }

    private void setSceneStyleSheets(Scene scene) {
        scene.getStylesheets().add(getClass().getResource("/styles/Base.css").toExternalForm());
    }

    public void switchToRegisterSceneOne() {
        Scene registerSceneOne = sceneMap.get("registerSceneOne");
        if (registerSceneOne == null) {
            try {
                Pane root = FXMLLoader.load(getClass().getResource("/views/register/RegistrationViewOne.fxml"));
                registerSceneOne = new Scene(root);
                sceneMap.put("registerSceneOne", registerSceneOne);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setSceneStyleSheets(registerSceneOne);
        primaryStage.setScene(registerSceneOne);
    }

    public void switchToRegisterSceneTwo() {
        Scene registerSceneTwo = sceneMap.get("registerSceneTwo");
        if (registerSceneTwo == null) {
            try {
                Pane root = FXMLLoader.load(getClass().getResource("/views/register/RegistrationViewTwo.fxml"));
                registerSceneTwo = new Scene(root);
                sceneMap.put("registerSceneTwo", registerSceneTwo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        setSceneStyleSheets(registerSceneTwo);
        primaryStage.setScene(registerSceneTwo);
    }

    public void switchToRegisterSceneThree() {
        Scene registerSceneThree = sceneMap.get("registerSceneThree");
        if (registerSceneThree == null) {
            try {
                Pane root = FXMLLoader.load(getClass().getResource("/views/register/RegistrationViewThree.fxml"));
                registerSceneThree = new Scene(root);
                sceneMap.put("registerSceneThree", registerSceneThree);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        setSceneStyleSheets(registerSceneThree);
        primaryStage.setScene(registerSceneThree);
    }

    public void switchToMainScene() {
        Scene mainScene = sceneMap.get("mainScene");
        if (mainScene == null) {
            try {
                BorderPane mainSceneBorderPane = FXMLLoader.load(getClass().getResource("/views/main/MainView.fxml"));
                paneCoordinator.initPane(mainSceneBorderPane);
                mainScene = new Scene(mainSceneBorderPane, 960, 530);
                sceneMap.put("mainScene", mainScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setWidth(primaryStage.getWidth() + 1);
        primaryStage.setHeight(primaryStage.getHeight() + 1);
        setSceneStyleSheets(mainScene);
        primaryStage.setScene(mainScene);
        primaryStage.setWidth(primaryStage.getWidth() - 1);
        primaryStage.setHeight(primaryStage.getHeight() - 1);
    }

    public void switchToAddGroupChatTwo() {

        Stage addGroupStage = stageMap.get("addGroupStage");
        if (addGroupStage != null) {
            setPopupStage(addGroupStage, "/views/add-group/AddGroupChatViewTwo.fxml");
        }
    }

    public void switchToConnectToServer() {
        Scene connectToServerScene = sceneMap.get("connectToServerScene");
        if (connectToServerScene == null) {
            try {
                Pane root = FXMLLoader.load(getClass().getResource("/views/connect-server/ConnectServerView.fxml"));
                connectToServerScene = new Scene(root);
                sceneMap.put("connectToServerScene", connectToServerScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ServiceFactory.getInstance().shutdown();
        setSceneStyleSheets(connectToServerScene);
        primaryStage.setScene(connectToServerScene);
    }

    public void clearSceneStagePaneMaps() {
        stageMap.clear();
        sceneMap.clear();
        paneCoordinator.clearPaneMap();
    }

    public void closeAutoDetectStage() {
        Stage stage = stageMap.get("autoDetectStage");
        if (stage != null) {
            stage.close();
        }
    }

    public void closeTryToConnectSplashStageStage() {
        Stage stage = stageMap.get("tryToConnectSplashStage");
        if (stage != null) {
            stage.close();
        }
    }


    public void closeAddContactStage() {
        Stage stage = stageMap.get("addContactStage");
        if (stage != null) {
            stage.close();
        }
    }

    public void closeAddGroupChatStage() {
        Stage stage = stageMap.get("addGroupStage");
        if (stage != null) {
            stage.close();
        }
    }

    public void closeLoadLoginDataSplashStage() {
        Stage loadLoginDataSplashStage = stageMap.get("loadLoginDataSplashStage");
        if (loadLoginDataSplashStage != null) {
            loadLoginDataSplashStage.close();
        }
    }

    public void closeRegisterUserSplashStage() {
        Stage registerUserSplashStage = stageMap.get("registerUserSplashStage");
        if (registerUserSplashStage != null) {
            registerUserSplashStage.close();
        }
    }

    public void closeAddSplashStage() {
        Stage addGroupSplashStage = stageMap.get("addSplashStage");
        if (addGroupSplashStage != null) {
            addGroupSplashStage.close();
        }
    }

    public void closeVoiceChatRinging() {
        Stage voiceChatRinging = stageMap.get("voiceChatRinging");
        if (voiceChatRinging != null) {
            voiceChatRinging.close();
        }
    }

    public void closeVoiceChatAcceptance() {
        Stage voiceChatAcceptance = stageMap.get("voiceChatAcceptance");
        if (voiceChatAcceptance != null) {
            voiceChatAcceptance.close();
        }
    }

    public void closeVoiceChatCall() {
        Stage voiceChatCallStage = stageMap.get("voiceChatCallStage");
        if (voiceChatCallStage != null) {
            voiceChatCallStage.close();
        }
    }


    public void showAddContactStage() {
        Stage addContactStage = new Stage();
        setPopupStageStyle(addContactStage);
        setPopupStage(addContactStage, "/views/add-contact/AddContactView.fxml");
        stageMap.put("addContactStage", addContactStage);
        addContactStage.show();

    }

    public void showAutoDetectStage() {
        Stage autoDetectStage = new Stage();
        setPopupStageStyle(autoDetectStage);
        setPopupStage(autoDetectStage, "/views/auto-detect/AutoDetect.fxml");
        stageMap.put("autoDetectStage", autoDetectStage);
        autoDetectStage.show();
    }

    public void showTryToConnectSplashStage() {
        Stage tryToConnectSplashStage = new Stage();
        setPopupStageStyle(tryToConnectSplashStage);
        setPopupStage(tryToConnectSplashStage, "/views/connect-server-splash/TryConnectSplash.fxml");
        stageMap.put("tryToConnectSplashStage", tryToConnectSplashStage);
        tryToConnectSplashStage.show();
    }

    public void showLoginLoadingDataSplashStage() {
        Stage loadLoginDataSplashStage = new Stage();
        setPopupStageStyle(loadLoginDataSplashStage);
        setPopupStage(loadLoginDataSplashStage, "/views/login/LoadDataSplash.fxml");
        stageMap.put("loadLoginDataSplashStage", loadLoginDataSplashStage);
        loadLoginDataSplashStage.show();
    }


    public void showRegisterUserSplashStage() {
        Stage registerUserSplashStage = new Stage();
        setPopupStageStyle(registerUserSplashStage);
        setPopupStage(registerUserSplashStage, "/views/register/RegisterUserSplash.fxml");
        stageMap.put("registerUserSplashStage", registerUserSplashStage);
        registerUserSplashStage.show();

    }


    public void showVoiceChatRinging() {
        Stage voiceChatRinging = new Stage();
        setVoiceChatStageStyle(voiceChatRinging);
        setPopupStage(voiceChatRinging, "/views/voice-chat/VoiceChatRinging.fxml");
        stageMap.put("voiceChatRinging", voiceChatRinging);
        voiceChatRinging.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            ModelFactory.getInstance().getVoiceChatModel().setInCall(false);
            VoiceSender.getInstance().closeCall();
            VoiceReceiver.getInstance().closeCall();
        });
        voiceChatRinging.show();
    }

    public void showVoiceChatAcceptance() {
        Stage voiceChatAcceptance = new Stage();
        setVoiceChatStageStyle(voiceChatAcceptance);
        setPopupStage(voiceChatAcceptance, "/views/voice-chat/VoiceChatAcceptance.fxml");
        stageMap.put("voiceChatAcceptance", voiceChatAcceptance);
        voiceChatAcceptance.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            ModelFactory.getInstance().getVoiceChatModel().setInCall(false);
            VoiceSender.getInstance().closeCall();
            VoiceReceiver.getInstance().closeCall();
        });
        voiceChatAcceptance.show();
    }


    public void showVoiceChatCallStage() {
        Stage voiceChatCallStage = new Stage();
        setVoiceChatStageStyle(voiceChatCallStage);
        setPopupStage(voiceChatCallStage, "/views/voice-chat/VoiceChatCall.fxml");
        stageMap.put("voiceChatCallStage", voiceChatCallStage);
        voiceChatCallStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            ModelFactory.getInstance().getVoiceChatModel().setInCall(false);
            VoiceSender.getInstance().closeCall();
            VoiceReceiver.getInstance().closeCall();
        });
        voiceChatCallStage.show();
    }


    private void setVoiceChatStageStyle(Stage stage) {
        stage.initStyle(StageStyle.TRANSPARENT);
    }

    public void showAddSplashStage() {
        Stage addSplashStage = new Stage();
        setPopupStageStyle(addSplashStage);
        setPopupStage(addSplashStage, "/views/add-splash/AddSplash.fxml");
        stageMap.put("addSplashStage", addSplashStage);
        addSplashStage.show();

    }

    public void showAddGroupStage() {
        Stage addGroupStage = new Stage();
        setPopupStageStyle(addGroupStage);
        setPopupStage(addGroupStage, "/views/add-group/AddGroupChatViewOne.fxml");
        stageMap.put("addGroupStage", addGroupStage);
        addGroupStage.show();
    }

    private void setPopupStageStyle(Stage stage) {
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    private void setPopupStage(Stage stage, String fxmlPath) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Pane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            setPopUpSceneStyle(scene);
            setSceneStyleSheets(scene);
            stage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPopUpSceneStyle(Scene scene) {
        scene.setFill(Color.TRANSPARENT);
        scene.getRoot().setEffect(new DropShadow(10, Color.rgb(30, 30, 30)));
    }

    public void showErrorNotification(String message) {
        SVGPath svgPath = new SVGPath();
        svgPath.setFill(Color.web("#EEEEEE"));
        svgPath.setContent("M16.5,22.5h3v3h-3Zm0-12h3v9h-3ZM17.985,3A15,15,0,1,0,33,18,14.993,14.993,0,0,0,17.985,3ZM18,30A12,12,0,1,1,30,18,12,12,0,0,1,18,30Z");
        svgPath.setScaleX(0.7);
        svgPath.setScaleY(0.7);

        Notifications thresholdNotification = Notifications.create()
                .text(message)
                .graphic(svgPath)
                .owner(primaryStage)
                .position(Pos.CENTER)
                .hideAfter(Duration.INDEFINITE);

        Notifications.create()
                .text(message)
                .graphic(svgPath)
                .owner(primaryStage)
                .position(Pos.CENTER)
                .threshold(1, thresholdNotification)
                .hideAfter(Duration.INDEFINITE)
                .show();
    }

    public void showMessageNotification(String senderName, String message) {
        Notifications.create()
                .title(senderName)
                .text(message)
                .hideAfter(Duration.seconds(3))
                .hideCloseButton()
                .show();
    }

    public void showAdminNotification(AnnouncementDto announcementDto) {
        var receivedAnnouncementBubble = new ReceivedAnnouncementBubble(announcementDto);

        Notifications.create()
                .graphic(receivedAnnouncementBubble)
                .position(Pos.BOTTOM_RIGHT)
                .hideAfter(Duration.INDEFINITE)
                .show();
    }
}
