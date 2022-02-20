package gov.iti.jets.network.util;

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
}
