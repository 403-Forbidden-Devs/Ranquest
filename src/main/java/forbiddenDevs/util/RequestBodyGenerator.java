package forbiddenDevs.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;
import forbiddenDevs.Constants;

import java.nio.file.FileAlreadyExistsException;
import java.util.Map;
import java.util.Vector;

public class RequestBodyGenerator {

    private static final Faker faker = new Faker();


    public static void generateFields(String jsonBody, Map<String, Integer> fields) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonBody);
        for (String field: fields.keySet()) {
            if(field.contains(Constants.NAME_FIELD)) {
                Vector<String> nameGeneratedSet = new Vector<>();
                 generateNames(jsonNode.get(field).asText(), nameGeneratedSet);
                 for(String name: nameGeneratedSet) {
                     ((ObjectNode) jsonNode).put(field, name);
                     String newJsonBody = objectMapper.writeValueAsString(jsonNode);
                     System.out.println("New json body: " + newJsonBody);
                 }
            }
        }
    }


    public static void generateNames(String originalData, Vector<String> nameSet, Integer length) {
        for(int i = 0; i < length; i++){
            nameSet.add(faker.name().fullName());
        }
    }

    public static void generateNames(String originalData, Vector<String> nameSet) {
        generateNames(originalData, nameSet,Constants.NAME_LENGTH); // default name length, if no length is provided
    }
}
