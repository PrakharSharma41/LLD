package connector;

import java.util.List;

public class ConnectorManager {
    private final SourceConnector source;
    private final DestinationConnector destination;

    public ConnectorManager(SourceConnector source, DestinationConnector destination) {
        this.source = source;
        this.destination = destination;
    }

    public void runSync(SyncContext context) {
        source.connect();
        destination.connect();

        List<Record> records = source.fetchRecords(context);
        Schema schema = source.getSchema();

        destination.writeRecords(records, schema);

        source.disconnect();
        destination.disconnect();
    }
}