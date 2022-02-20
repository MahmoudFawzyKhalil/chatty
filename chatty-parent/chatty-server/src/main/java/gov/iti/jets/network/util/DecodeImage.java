package gov.iti.jets.network.util;

import java.io.IOException;

public interface DecodeImage {
    boolean save(String imgBase64,String path) throws IOException;
}
