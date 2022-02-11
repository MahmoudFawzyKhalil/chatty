package gov.iti.jets.presentation.customcontrols;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupChatMenuItem extends VBox implements Initializable {
    @FXML
    private Label groupChatNameLabel;

    @FXML
    private Circle groupChatPhotoCircle;

    GroupChatMenuItem(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main/GroupChatMenuItem.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
