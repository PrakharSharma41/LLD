import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataProcessingEngine {
    private final Parser parser;
    private final Validator validator;
    private final Transformer transformer;
    private final DataSink sink;

    public DataProcessingEngine(Parser parser, Validator validator,
                                Transformer transformer, DataSink sink) {
        this.parser = parser;
        this.validator = validator;
        this.transformer = transformer;
        this.sink = sink;
    }

    public void execute(DataSource source) {
        try (InputStream input = source.fetchData()) {
            List<DataRecord> records = parser.parse(input);
            List<DataRecord> processed = new ArrayList<>();
            for (DataRecord record : records) {
                if (validator.validate(record)) {
                    processed.add(transformer.transform(record));
                } else {
                    System.err.println("Invalid record: " + record);
                }
            }
            sink.write(processed);
        } catch (Exception e) {
            e.printStackTrace();  // Log error properly
        }
    }
}
