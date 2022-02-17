package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.presentation.models.UserModel;
import javafx.scene.image.Image;

public class ImageMapper {

    private static ImageMapper INSTANCE = new ImageMapper();

    public static ImageMapper getInstance(){
        return INSTANCE;
    }

    public String imageToEncodedString(Image value) {
        return "encoded string";
    }

    public Image encodedStringToImage(String encodedString) {
        if (encodedString == null || encodedString.isEmpty()){
            return UserModel.DEFAULT_IMAGE;
        }
        // TODO decode image
        return null;
    }
}
