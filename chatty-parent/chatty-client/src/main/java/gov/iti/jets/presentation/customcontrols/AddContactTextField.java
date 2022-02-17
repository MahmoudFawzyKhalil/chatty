package gov.iti.jets.presentation.customcontrols;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddContactTextField extends TextField implements Initializable {
    @FXML
    private TextField contactPhoneNumberTextField;
    private Pane parent;
    public AddContactTextField(Pane parent){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add-contact/AddContactTextField.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.parent=parent;
        setTextFieldListener();
    }

    private void setTextFieldListener() {
        contactPhoneNumberTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
//            System.out.println(newValue.isEmpty());
            int myIndex=parent.getChildren().indexOf(this);
            if(!newValue.isEmpty()&&myIndex==parent.getChildren().size()-1)
                parent.getChildren().add(new AddContactTextField(parent));
            /*else if(newValue.isEmpty()&&myIndex==parent.getChildren().size()-2&&)*/
        }));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
