package forbiddenDevs;

import com.fasterxml.jackson.core.JsonProcessingException;
import forbiddenDevs.generator.RequestBodyGenerator;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if(args.length >= 1) {
            String jsonBody = args[1];
            String field = args[2];
            String total = args[3];
            Map<String, Integer> testField = Map.of(field, Integer.parseInt(total));
            try{
                RequestBodyGenerator.generateFields(jsonBody, testField);
            }
            catch (JsonProcessingException ex) {
                System.out.println("Thing errored out with " + ex.getMessage());
            }
        }

    }
}
