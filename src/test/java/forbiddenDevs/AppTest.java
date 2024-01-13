package forbiddenDevs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import forbiddenDevs.generator.RequestBodyGenerator;
import forbiddenDevs.util.CommonUtils;
import org.junit.Test;

import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    String fileOutput = "src/test/java/forbiddenDevs/testoutput/result.json";
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void checkIfNameIsBeingGenerated() {
        String jsonBody = "{ \"name\": \"John Doe\", \"age\": 25, \"city\": \"New York\" }";
        Map<String, Integer> fields = Map.of("name", 2);
        try {
            RequestBodyGenerator.generateFields(jsonBody, fields, fileOutput);
        } catch(JsonProcessingException ex){
            System.out.println("failed at processing json: " + ex.getMessage());
        }
    }

    @Test
    public void checkIfJsonFileIsGivingOutResponse() {
        String jsonBody = CommonUtils.getStringFromJson();
        Map<String, Integer> fields = Map.of("name", 4);
        try {
            RequestBodyGenerator.generateFields(jsonBody, fields, fileOutput);
        } catch(JsonProcessingException ex){
            System.out.println("failed at processing json: " + ex.getMessage());
        }
    }

    @Test
    public void checkWhenNestedFieldArePassedInRequest() {
        String filePath = "src/test/java/forbiddenDevs/testData/req1.json";
        String jsonBody = CommonUtils.getStringFromJson(filePath);
        Map<String, Integer> fields = Map.of("name", 4);
        try {
            RequestBodyGenerator.generateFields(jsonBody, fields, fileOutput);
        } catch(JsonProcessingException ex){
            System.out.println("failed at processing json: " + ex.getMessage());
        }
    }
}
