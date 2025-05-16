import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public class CSVParser implements Parser {
    @Override
    public List<DataRecord> parse(InputStream input) throws ParseException {
        // use Jackson or Gson
        return null;
    }
}
