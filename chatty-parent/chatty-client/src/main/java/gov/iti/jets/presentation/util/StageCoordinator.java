package gov.iti.jets.presentation.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
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

    private Stage primaryStage;
    private final Map<String, Scene> sceneMap = new HashMap<>();

    private final Map<String,Stage>stageMap = new HashMap<>();

    private StageCoordinator() {}

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

    public void switchToMainScene() {
        Scene mainScene = sceneMap.get("mainScene");
        if (mainScene == null){
            try {
                Pane root = FXMLLoader.load(getClass().getResource("/views/main/MainView.fxml"));
                mainScene = new Scene(root);
                sceneMap.put("mainScene", mainScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        primaryStage.setScene(mainScene);
    }
    /*
        MAHMOUD
     */


    /*
        MAHMOUD
     */



    /*
        OSAMA
     */

    public void closeAddContactScene(){
        stageMap.get("addContactStage").close();
    }

    public void closeGroupContactScene(){
        stageMap.get("addGroupStage").close();
    }
    public void showAddContactStage(){
        Stage addContactStage=stageMap.get("addContactStage");
        if(addContactStage==null){
            addContactStage=new Stage();
            setPopupStage(addContactStage,"/views/add-contact/AddContactView.fxml");
            stageMap.put("addContactStage",addContactStage);
        }
        addContactStage.show();

    }

    public void showAddGroupStage(){
        Stage addGroupStage=stageMap.get("addGroupStage");
        if(addGroupStage==null){
            addGroupStage=new Stage();
            setPopupStage(addGroupStage,"/views/add-group/AddGroupChatViewOne.fxml");
            stageMap.put("addGroupStage",addGroupStage);
        }
        addGroupStage.show();

    }

    private void setPopupStage(Stage stage,String fxmlPath) {
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            Pane root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene=new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            scene.getRoot().setEffect(new DropShadow(10, Color.rgb(30, 30, 30)));
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
        OSAMA
     */


}
