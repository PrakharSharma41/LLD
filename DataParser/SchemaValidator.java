public class SchemaValidator implements Validator {
    private final Schema schema;

    public SchemaValidator(Schema schema) {
        this.schema = schema;
    }

    @Override
    public boolean validate(DataRecord record) {
        // implement schema validation logic
        return true;
    }
}