package forbiddenDevs.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class RanquestData {
    private String jsonBody;
    private List<String> fields;
    private Integer times;
}


