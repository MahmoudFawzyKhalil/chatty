package gov.iti.jets.presentation.customcontrols;

import gov.iti.jets.presentation.util.UiValidator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import net.synedra.validatorfx.Validator;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class AddContactTextField extends TextField implements Initializable {
    @FXML
    private TextField contactPhoneNumberTextField;
    private VBox textFieldAddContactViewVBox;
    private List<TextField> list;
    private Button addContactButton;
    private Validator validator = UiValidator.getInstance().createValidator();

    public AddContactTextField(VBox textFieldAddContactViewVBox, List<TextField> list, Button addContactButton) {
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
        this.textFieldAddContactViewVBox = textFieldAddContactViewVBox;
        setTextFieldListener();
        validatePhoneNumberTextField();
        addEnableButtonValidationListener();
    }

    private void setTextFieldListener() {
        contactPhoneNumberTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            int lastTextFieldIndex = textFieldAddContactViewVBox.getChildren().indexOf(this);

            if (!newValue.isEmpty() && lastTextFieldIndex == textFieldAddContactViewVBox.getChildren().size() - 1) {
                textFieldAddContactViewVBox.getChildren().add(new AddContactTextField(textFieldAddContactViewVBox, list, addContactButton));
            }
        }));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void validatePhoneNumberTextField() {
        validator.createCheck()
                .dependsOn("contactPhoneNumber", contactPhoneNumberTextField.textProperty())
                .withMethod(c -> {
                    String phoneNumber = c.get("contactPhoneNumber");
                    if (!UiValidator.EGY_PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
                        c.error("Please enter a valid 11 digit phone number.");
                        addContactButton.setDisable(true);
                    }
                })
                .decorates(contactPhoneNumberTextField)
                .immediate();
    }

    private void removeAllEmpty() {
        for (int i = 0; i < textFieldAddContactViewVBox.getChildren().size() - 1; i++) {
            TextField textField = (TextField) textFieldAddContactViewVBox.getChildren().get(i);
            if (textField.getText().trim().isEmpty()) {
                textFieldAddContactViewVBox.getChildren().remove(i--);
            }
        }
    }

    private boolean isAllValid() {
        for (int i = 0; i < textFieldAddContactViewVBox.getChildren().size() - 1; i++) {
            TextField textField = (TextField) textFieldAddContactViewVBox.getChildren().get(i);
            if (!UiValidator.EGY_PHONE_NUMBER_PATTERN.matcher(textField.getText()).matches()) {
                return false;
            }
        }
        TextField textField = (TextField) textFieldAddContactViewVBox.getChildren().get(0);
        if (textFieldAddContactViewVBox.getChildren().size() == 1 && !UiValidator.EGY_PHONE_NUMBER_PATTERN.matcher(textField.getText()).matches()) {
            return false;
        }
        return true;
    }

    private void addEnableButtonValidationListener() {
        this.textProperty().addListener((observable, old, newval) -> {
            if (newval.trim().isEmpty()) {
                removeAllEmpty();
            }
            if (isAllValid()) {
                addContactButton.setDisable(false);
            }
        });
    }
}
