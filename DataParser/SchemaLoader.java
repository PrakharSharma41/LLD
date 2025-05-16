public class SchemaLoader {
    public static Schema loadSchema(String sourceType) {
        Schema schema = new Schema();
        switch (sourceType) {
            case "user":
                schema.addField("id", Integer.class);
                schema.addField("name", String.class);
                schema.addField("email", String.class);
                break;
            case "order":
                schema.addField("orderId", String.class);
                schema.addField("amount", Double.class);
                break;
        }
        return schema;
    }
}
