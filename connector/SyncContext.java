package connector;

import java.time.Instant;
import java.util.Map;

public class SyncContext {
    private Instant lastSyncedAt;
    private Map<String, String> config;

    public SyncContext(Instant lastSyncedAt, Map<String, String> config) {
        this.lastSyncedAt = lastSyncedAt;
        this.config = config;
    }

    public Instant getLastSyncedAt() {
        return lastSyncedAt;
    }

    public Map<String, String> getConfig() {
        return config;
    }
}