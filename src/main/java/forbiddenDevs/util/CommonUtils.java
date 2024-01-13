package forbiddenDevs.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
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

    public static void updateFieldValue(JsonNode node, String fieldName, String newValue) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;

            // Check if the current object has the field to update
            if (objectNode.has(fieldName)) {
                // Update the field value
                objectNode.put(fieldName, newValue);
            }

            // Recursively update fields in child nodes
            objectNode.fields().forEachRemaining(entry -> updateFieldValue(entry.getValue(), fieldName, newValue));
        } else if (node.isArray()) {
            // Recursively update fields in array elements
            node.elements().forEachRemaining(element -> updateFieldValue(element, fieldName, newValue));
        }
    }


    public static void writeJsonToFile(String json, String filePath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), objectMapper.readTree(json));
    }

}
