package gov.iti.jets.presentation.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class UserModel {
    private StringProperty username = new SimpleStringProperty();
    private ObjectProperty<Image> userImage = new SimpleObjectProperty<>();

    public String getUserName() {
        return username.get();
    }

    public StringProperty userNameProperty() {
        return username;
    }

    public void setUserName(String userName) {
        this.username.set(userName);
    }

    public Image getUserImage() {
        return userImage.get();
    }

    public ObjectProperty<Image> userImageProperty() {
        return userImage;
    }

    public void setUserImage(Image userImage) {
        this.userImage.set(userImage);
    }

    public UserModel(){
        setUserImage(new Image(getClass().getResource("/images/user.png").toString()));
    }
}
