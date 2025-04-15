package hitCounter;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class MultithreadApproach {
    private final AtomicIntegerArray hits = new AtomicIntegerArray(300);
    private final AtomicIntegerArray timeStamp = new AtomicIntegerArray(300);

    public void hit(int time) {
        int idx = time % 300;
        int oldTime = timeStamp.get(idx);

        if (oldTime != time) {
            // Ensure only one thread updates the timestamp and resets the counter
            if (timeStamp.compareAndSet(idx, oldTime, time)) {
                hits.set(idx, 1); // Reset count for the new timestamp
            }
        } else {
            hits.incrementAndGet(idx); // Safe atomic increment
        }
    }

    public int getHits(int time) {
        int res = 0;
        for (int i = 0; i < 300; ++i) {
            if (time - timeStamp.get(i) < 300) {
                res += hits.get(i);
            }
        }
        return res;
    }    
}
