package connector;

import java.util.Map;

public class Schema {
    private Map<String, String> fields;

    public Schema(Map<String, String> fields) {
        this.fields = fields;
    }

    public Map<String, String> getFields() {
        return fields;
    }
}