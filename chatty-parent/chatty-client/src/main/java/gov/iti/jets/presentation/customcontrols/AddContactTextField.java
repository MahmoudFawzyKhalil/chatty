package gov.iti.jets.presentation.customcontrols;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddContactTextField extends TextField implements Initializable {
    @FXML
    private TextField contactPhoneNumberTextField;

    public AddContactTextField(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add-contact/AddContactTextField.fxml"));
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
