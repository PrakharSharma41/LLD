import java.util.Map;

public class Schema {
    private Map<String, Class<?>> fieldTypes;
    public void addField(String fieldName, Class<?> fieldType) {
        fieldTypes.put(fieldName, fieldType);
    }

    public Class<?> getFieldType(String fieldName) {
        return fieldTypes.get(fieldName);
    }

    public boolean hasField(String fieldName) {
        return fieldTypes.containsKey(fieldName);
    }

    public Map<String, Class<?>> getAllFields() {
        return fieldTypes;
    }    
}
// Schema is used for validation

// Ensures the incoming data is correct and complete.

// Checks:

// Required fields are present.

// Field types match (e.g., age should be an Integer).

// Custom constraints (e.g., salary > 0).