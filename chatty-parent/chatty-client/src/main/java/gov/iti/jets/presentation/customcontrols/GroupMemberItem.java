package gov.iti.jets.presentation.customcontrols;

import gov.iti.jets.presentation.models.ContactModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupMemberItem extends HBox implements Initializable {

    @FXML
    private Label contactNameLabel;
    @FXML
    private Region selectedRegion;

    public GroupMemberItem(ContactModel contactModel){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add-group/GroupMemberItem.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        contactNameLabel.textProperty().bind(Bindings.concat(contactModel.displayNameProperty(),"#",contactModel.phoneNumberProperty()));
        selectedRegion.visibleProperty().bind(contactModel.selectedProperty());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
