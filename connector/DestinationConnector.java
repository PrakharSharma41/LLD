package connector;

// Destination Connector Interface
public interface DestinationConnector extends Connector {
    void writeRecords(List<Record> records, Schema schema);
}