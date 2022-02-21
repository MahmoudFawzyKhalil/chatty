package gov.iti.jets.presentation.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StageCoordinator {
    private static final StageCoordinator stageCoordinator = new StageCoordinator();

    private Stage primaryStage;

    private final Map<String, Scene> sceneMap = new HashMap<>();

    private StageCoordinator(){

    }

    public static StageCoordinator getInstance(){
        return stageCoordinator;
    }

    public void initStage(Stage primaryStage){
        this.primaryStage = primaryStage;

    }

    public void switchToLoginScene(){
        Scene loginScene = sceneMap.get("loginScene");
        if (loginScene == null){
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


    public void switchToMainScene() {
        Scene mainScene = sceneMap.get("mainScene");
        if (mainScene == null) {
            try {
                BorderPane mainSceneBorderPane = FXMLLoader.load(getClass().getResource("/views/main/MainView.fxml"));
//                paneCoordinator.initPane(mainSceneBorderPane);
                mainScene = new Scene(mainSceneBorderPane, 960, 530);
                sceneMap.put("mainScene", mainScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setWidth(961);
        primaryStage.setHeight(531);
//        setSceneStyleSheets(mainScene);
        primaryStage.setScene(mainScene);
    }

    public void setStageResizable(boolean value){
        primaryStage.setResizable(value);
    }
}
