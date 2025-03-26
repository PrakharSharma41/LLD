package hitCounter;

public class SynchronizedApproach {
    private final int[] hits = new int[300];
    private final int[] timeStamp = new int[300];

    public synchronized void hit(int time) {
        int idx = time % 300;
        if (timeStamp[idx] != time) {
            hits[idx] = 1;  // Reset count for the new timestamp
            timeStamp[idx] = time;
        } else {
            hits[idx]++;  // Safe increment since method is synchronized
        }
    }

    public synchronized int getHits(int time) {
        int res = 0;
        for (int i = 0; i < 300; ++i) {
            if (time - timeStamp[i] < 300) {
                res += hits[i];
            }
        }
        return res;
    }
}
