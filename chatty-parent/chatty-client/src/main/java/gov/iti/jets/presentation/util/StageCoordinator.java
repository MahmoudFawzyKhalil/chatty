package gov.iti.jets.presentation.util;

import gov.iti.jets.presentation.controllers.AddGroupChatController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

        primaryStage.setScene(loginScene);
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

        primaryStage.setScene(registerSceneThree);
    }

    public void switchToMainScene() {
        Scene mainScene = sceneMap.get("mainScene");
        if (mainScene == null) {
            try {
                BorderPane mainSceneBorderPane = FXMLLoader.load(getClass().getResource("/views/main/MainView.fxml"));
                paneCoordinator.initPane(mainSceneBorderPane);
                mainScene = new Scene(mainSceneBorderPane);
                sceneMap.put("mainScene", mainScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        primaryStage.setScene(mainScene);
    }

    public void switchToAddGroupChatTwo(){
        Scene addGroupSceneTwo = sceneMap.get("addGroupSceneTwo");
        if(addGroupSceneTwo!=null){
            Stage stage=stageMap.get("addGroupStage");
            stage.setScene(addGroupSceneTwo);
        }
    }

    public void clearSceneStagePaneMaps() {
        stageMap.clear();
        sceneMap.clear();
        paneCoordinator.clearPaneMap();
    }

    public void closeAddContactScene() {
        stageMap.get("addContactStage").close();
    }

    public void closeGroupContactScene() {
        stageMap.get("addGroupStage").close();
    }

    public void showAddContactStage() {
        Stage addContactStage = new Stage();
        setPopupStageStyle(addContactStage);
        setPopupStage(addContactStage, "/views/add-contact/AddContactView.fxml");
        stageMap.put("addContactStage", addContactStage);
        addContactStage.show();

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
            loadWithController(fxmlLoader.getController(),"/views/add-group/AddGroupChatViewTwo.fxml");
            Scene scene = new Scene(root);
            setPopUpSceneStyle(scene);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWithController(AddGroupChatController controller, String fxmlPath) {
        try {
            FXMLLoader currLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            currLoader.setController(controller);
            Scene scene = new Scene(currLoader.load());
            setPopUpSceneStyle(scene);
            sceneMap.put("addGroupSceneTwo", scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPopUpSceneStyle(Scene scene) {
        scene.setFill(Color.TRANSPARENT);
        scene.getRoot().setEffect(new DropShadow(10, Color.rgb(30, 30, 30)));
    }
}
