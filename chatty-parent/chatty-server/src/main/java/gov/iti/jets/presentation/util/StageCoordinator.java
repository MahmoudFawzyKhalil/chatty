package gov.iti.jets.presentation.util;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
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

    private StageCoordinator() {
    }

    public static StageCoordinator getInstance() {
        return stageCoordinator;
    }

    public void initStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void setSceneStyleSheets(Scene scene) {
        scene.getStylesheets().add(getClass().getResource("/styles/Base.css").toExternalForm());
    }

    public void switchToMainServerScene() {
        Scene mainServerScene = sceneMap.get("mainServerScene");
        if (mainServerScene == null) {
            try {
                BorderPane mainSceneBorderPane = FXMLLoader.load(getClass().getResource("/views/main/MainServerView.fxml"));
                paneCoordinator.initPane(mainSceneBorderPane);
                mainServerScene = new Scene(mainSceneBorderPane, 960, 530);
                sceneMap.put("mainServerScene", mainServerScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setWidth(961);
        primaryStage.setHeight(531);
        setSceneStyleSheets(mainServerScene);
        primaryStage.setScene(mainServerScene);
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
}
