package connector;

// Source Connector Interface
public interface SourceConnector extends Connector {
    List<Record> fetchRecords(SyncContext context);
    Schema getSchema();
}