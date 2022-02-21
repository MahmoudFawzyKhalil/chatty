package gov.iti.jets.repository.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageDbUtil {
    private static final ImageDbUtil imageDbUtil = new ImageDbUtil();
    private final Path currentRelativePath = Paths.get("");
    private final String dbPath = currentRelativePath.toAbsolutePath().toString() + "/DB/";
    private final String profileDbPath = dbPath + "/profile-pic/";
    private final String groupDbPath = dbPath + "/group-pic/";

    private ImageDbUtil() {
        File dbFolder = new File(dbPath);
        dbFolder.mkdir();
        File profileDbFolder = new File(profileDbPath);
        profileDbFolder.mkdir();
        File groupDbFolder = new File(profileDbPath);
        groupDbFolder.mkdir();
    }

    public static ImageDbUtil getInstance() {
        return imageDbUtil;
    }

    public String getDbPath() {
        return dbPath;
    }

    public String getProfileDbPath() {
        return profileDbPath;
    }

    public String getGroupDbPath() {
        return groupDbPath;
    }
}
