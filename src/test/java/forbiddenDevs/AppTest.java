package forbiddenDevs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import forbiddenDevs.util.RequestBodyGenerator;
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
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void checkIfLibIsWorking() {
        String jsonBody = "{ \"name\": \"John Doe\", \"age\": 25, \"city\": \"New York\" }";
        Map<String, Integer> fields = Map.of("name", 2);
        try {
            RequestBodyGenerator.generateFields(jsonBody, fields);
        } catch(JsonProcessingException ex){
            System.out.println("failed at processing json: " + ex.getMessage());
        }
    }
}
