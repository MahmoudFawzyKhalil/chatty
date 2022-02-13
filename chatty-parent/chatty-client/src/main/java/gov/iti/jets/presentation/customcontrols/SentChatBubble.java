package gov.iti.jets.presentation.customcontrols;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SentChatBubble extends HBox implements Initializable {

    @FXML
    private VBox messageBackgroundVbox;

    @FXML
    private Text messageText;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label timeStampLabel;

    @FXML
    private Circle userProfilePicCircle;

    public SentChatBubble(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/chat/SentChatBubble.fxml"));
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
