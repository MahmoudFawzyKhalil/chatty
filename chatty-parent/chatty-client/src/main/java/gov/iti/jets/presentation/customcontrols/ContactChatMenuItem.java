package gov.iti.jets.presentation.customcontrols;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactChatMenuItem extends HBox implements Initializable {


    public ContactChatMenuItem() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main/ContactChatMenuItem.fxml"));
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