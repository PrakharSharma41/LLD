import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public interface Parser {
    List<DataRecord> parse(InputStream input) throws ParseException;
}
