import java.util.List;

public interface DataSink {
    void write(List<DataRecord> records);
}