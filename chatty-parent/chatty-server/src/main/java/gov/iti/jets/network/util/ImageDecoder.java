package gov.iti.jets.network.util;

import java.io.IOException;

public interface ImageDecoder {
    boolean save(String imgBase64, String path) throws IOException;

    String decode(String imgPath);
}
