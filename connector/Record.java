package connector;

import java.time.Instant;
import java.util.Map;

public class Record {
    private Map<String, Object> data;
    private Instant timestamp;

    public Record(Map<String, Object> data, Instant timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}