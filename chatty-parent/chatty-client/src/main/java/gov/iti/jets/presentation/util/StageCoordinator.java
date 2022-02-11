package gov.iti.jets.presentation.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    public void switchToRegisterSceneOne(){
        Scene registerSceneOne = sceneMap.get("registerSceneOne");
        if (registerSceneOne == null){
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

    public void switchToRegisterSceneTwo(){
        Scene registerSceneTwo = sceneMap.get("registerSceneTwo");
        if (registerSceneTwo == null){
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

    public void switchToRegisterSceneThree(){
        Scene registerSceneThree = sceneMap.get("registerSceneThree");
        if (registerSceneThree == null){
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

    public void switchToChatScene() {
        Scene chatScene = sceneMap.get("chatScene");
        if (chatScene == null){
            try {
                Pane root = FXMLLoader.load(getClass().getResource("/views/chat/ChatView.fxml"));
                chatScene = new Scene(root);
                sceneMap.put("chatScene", chatScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        primaryStage.setScene(chatScene);
    }

    public void setStageResizable(boolean value){
        primaryStage.setResizable(value);
    }
}
