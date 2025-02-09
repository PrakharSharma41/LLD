package MeetingSchedulerOLD;

import java.util.concurrent.*;

public class AuditLogCleaner {
    private ScheduledExecutorService scheduler;

    public AuditLogCleaner(RoomManager roomManager, int cleanupInterval) {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> roomManager.cleanupLogs(cleanupInterval),
                0, cleanupInterval, TimeUnit.DAYS);
    }

    public void stop() {
        scheduler.shutdown();
    }
}
