package gov.iti.jets.presentation.customcontrols;

import gov.iti.jets.presentation.util.UiValidator;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private Pane textFieldAddContactViewVBox;
    private List<TextField> list;
    private Button addContactButton;
    private Validator validator = UiValidator.getInstance().createValidator();

    public AddContactTextField( Pane textFieldAddContactViewVBox, List<TextField> list, Button addContactButton ) {
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "/views/add-contact/AddContactTextField.fxml" ) );
        loader.setController( this );
        loader.setRoot( this );
        this.list = list;
        this.addContactButton = addContactButton;
        list.add( this );
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.textFieldAddContactViewVBox = textFieldAddContactViewVBox;
        setTextFieldListener();
        validatePhoneNumberTextField();
        addEnableButtonValidationListener();
    }

    private void setTextFieldListener() {
        contactPhoneNumberTextField.textProperty().addListener( (( observable, oldValue, newValue ) -> {
            int lastTextFieldIndex = textFieldAddContactViewVBox.getChildren().indexOf( this );

            if (!newValue.isEmpty() && lastTextFieldIndex == textFieldAddContactViewVBox.getChildren().size() - 1) {
                textFieldAddContactViewVBox.getChildren().add( new AddContactTextField( textFieldAddContactViewVBox, list, addContactButton ) );
            }
        }) );
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

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
            if (!validator.containsErrors()) {
                addContactButton.setDisable( false );
            }
        } );

        textFieldAddContactViewVBox.getChildren().addListener( new ListChangeListener<Node>() {
            @Override
            public void onChanged( Change<? extends Node> c ) {
                if (c.getList().isEmpty()){
                    return;
                }
                var lastElement = (AddContactTextField) c.getList().get( c.getList().size() - 1 );
                if (lastElement.textProperty().isEmpty().get()) {
                    addContactButton.setDisable( false );
                }
            }
        } );
    }
}
