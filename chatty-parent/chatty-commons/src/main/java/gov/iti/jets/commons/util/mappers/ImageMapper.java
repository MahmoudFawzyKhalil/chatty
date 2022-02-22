package gov.iti.jets.commons.util.mappers;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageMapper {

    private static ImageMapper INSTANCE = new ImageMapper();
    private final Image defaultImage = new Image(ImageMapper.class.getResource("/assets/person.png").toString());
    private String encodedDefaultImage;

    public static ImageMapper getInstance() {
        return INSTANCE;
    }

    public ImageMapper() {
        try {
            byte[] bytes = convertToBytes(defaultImage);
            encodedDefaultImage = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException ioException2) {
            encodedDefaultImage = "";
        }

    }

    public String imageToEncodedString(Image value) {
        try {
            byte[] bytes = convertToBytes(value);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException ioException) {
            return encodedDefaultImage;
        }
    }

    private byte[] convertToBytes(Image image) throws IOException {
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public Image encodedStringToImage(String encodedString) {
        if (encodedString == null || encodedString.isEmpty()) {
            return defaultImage;
        }
        byte[] bytes;
        bytes = Base64.getDecoder().decode(encodedString);
        return new Image(new ByteArrayInputStream(bytes));

    }
}
