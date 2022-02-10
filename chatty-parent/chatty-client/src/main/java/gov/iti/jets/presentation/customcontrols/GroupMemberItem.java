package gov.iti.jets.presentation.customcontrols;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupMemberItem extends HBox implements Initializable {

    @FXML
    private CheckBox contactNameCheckBox;

    public GroupMemberItem(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add-group/GroupMemberItem.fxml"));
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
