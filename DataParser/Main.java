public class Main {
    public static void main(String[] args) {
        Parser parser = ParserFactory.getParser("csv");
        Validator validator = new SchemaValidator(SchemaLoader.loadSchema(null));
        Transformer transformer = new SimpleTransformer();
        DataSink sink = new DatabaseSink();
        DataSource source = new FileSource("input.csv");
    
        DataProcessingEngine engine = new DataProcessingEngine(parser, validator, transformer, sink);
        engine.execute(source);
    }
}
