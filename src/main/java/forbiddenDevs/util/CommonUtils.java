package forbiddenDevs.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonUtils {

    public static String getStringFromJson() {
        String filePath = "src/test/java/forbiddenDevs/testdata/req.json";
        return getStringFromJson(filePath);
    }

    public static String getStringFromJson(String filePath) {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
            return new String(jsonData);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
