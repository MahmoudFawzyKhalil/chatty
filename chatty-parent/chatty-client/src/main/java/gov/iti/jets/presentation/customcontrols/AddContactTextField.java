package gov.iti.jets.presentation.customcontrols;

import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.UiValidator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import net.synedra.validatorfx.Validator;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class AddContactTextField extends TextField implements Initializable {
    @FXML
    private TextField contactPhoneNumberTextField;

    private Pane parent;
    private List<TextField> list;
    private Button addContactButton;
    private Validator validator = UiValidator.getInstance().createValidator();
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();


    public AddContactTextField(Pane parent, List<TextField> list, Button addContactButton){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add-contact/AddContactTextField.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        this.list = list;
        this.addContactButton = addContactButton;
        list.add(this);
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
            int myIndex = parent.getChildren().indexOf(this);
            if (myIndex >= 3){
                stageCoordinator.showErrorNotification( "sorry maximum requests are 3 requests." );
            }else{
                if(!newValue.isEmpty() && myIndex==parent.getChildren().size()-1){
                    parent.getChildren().add(new AddContactTextField(parent,list, addContactButton));
                }/*else if(newValue.isEmpty()&&myIndex==parent.getChildren().size()-2&&)*/

            }
        }));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validatePhoneNumberTextField();
        addEnableButtonValidationListener();
    }

    private void validatePhoneNumberTextField() {
        validator.createCheck()
                .dependsOn( "contactPhoneNumber", contactPhoneNumberTextField.textProperty() )
                .withMethod( c -> {
                    String phoneNumber = c.get( "contactPhoneNumber" );
                    if (!UiValidator.PHONE_NUMBER_PATTERN.matcher( phoneNumber ).matches()) {
                        c.error( "Please enter a valid 11 digit phone number." );
                        addContactButton.setDisable( true );
                    }
                } )
                .decorates( contactPhoneNumberTextField )
                .immediate();
    }
    private void addEnableButtonValidationListener() {
        validator.containsErrorsProperty().addListener( e -> {
            if (!validator.containsErrors() || this.getText().equals("")){
                addContactButton.setDisable( false );
            }
        } );
    }
}
