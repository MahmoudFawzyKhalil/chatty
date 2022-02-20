package gov.iti.jets.network.util;

import gov.iti.jets.commons.util.mappers.ImageMapper;
import javafx.scene.image.Image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

public class ImageDecoderImpl implements ImageDecoder {
    @Override
    public boolean save(String imgBase64,String path) throws IOException {
        byte[] data = Base64.getDecoder().decode(imgBase64);
        try (OutputStream stream = new FileOutputStream(path)) {
            stream.write(data);
            return true;
        }
    }

    @Override
    public String decode(String imgPath) {
        Image image = new Image(imgPath);
        return ImageMapper.getInstance().imageToEncodedString(image);
    }
}
