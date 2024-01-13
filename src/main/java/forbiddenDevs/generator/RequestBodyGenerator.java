package forbiddenDevs.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;
import forbiddenDevs.Constants;
import forbiddenDevs.util.CommonUtils;

import java.nio.file.FileAlreadyExistsException;
import java.util.Map;
import java.util.Vector;

public class RequestBodyGenerator {

    private static final Faker faker = new Faker();


    /*
    Arguments:
    String jsonBody - json body which will be modified
    Map<String, Integer> fields - String represents name of the key, Integer represents number
    of bodies to generate

     */
    public static void generateFields(String jsonBody, Map<String, Integer> fields, String outputFilePath) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonBody);
        for (String field: fields.keySet()) {
            if(field.contains(Constants.NAME_FIELD) || field.contains(Constants.FULL_NAME_FIELD)) {
                Vector<String> nameGeneratedSet = new Vector<>();
                Integer totals = 1;
                if(!fields.get(field).equals(0)) totals = fields.get(field);
                generateNames(nameGeneratedSet, totals);
                for(String name: nameGeneratedSet) {
                     CommonUtils.updateFieldValue(jsonNode, field, name);
                     String newJsonBody = objectMapper.writeValueAsString(jsonNode);
                     try {
                         CommonUtils.writeJsonToFile(newJsonBody, outputFilePath);
                     }
                     catch (Exception ex) {
                         System.out.println("Errored out while writing to file with message:"+ex.getMessage());
                     }
                     System.out.println("New json body: " + newJsonBody);
                }
            }
        }
    }




    public static void generateNames(Vector<String> nameSet, Integer length) {
        for(int i = 0; i < length; i++){
            nameSet.add(faker.name().fullName());
        }
    }

    public static void generateNames(Vector<String> nameSet) {
        generateNames(nameSet,Constants.NAME_LENGTH); // default name length, if no length is provided
    }
}
