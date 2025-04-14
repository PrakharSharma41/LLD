import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class GroupManager {
    private int democrats = 0;
    private int republicans = 0;
    private final Lock lock = new ReentrantLock();
    private final Semaphore demQueue = new Semaphore(0);
    private final Semaphore repQueue = new Semaphore(0);
    private int finishedCount = 0;
    private final Condition groupDone = lock.newCondition();

    public void enterAndVote(Party party, Runnable vote) throws InterruptedException {
        Semaphore myQueue = (party == Party.DEMOCRAT) ? demQueue : repQueue;

        lock.lock();
        if (party == Party.DEMOCRAT) democrats++;
        else republicans++;

        if (democrats >= 3) {
            demQueue.release(3);
            democrats -= 3;
        } else if (republicans >= 3) {
            repQueue.release(3);
            republicans -= 3;
        } else if (democrats >= 2 && republicans >= 1) {
            demQueue.release(2);
            repQueue.release(1);
            democrats -= 2;
            republicans -= 1;
        } else if (republicans >= 2 && democrats >= 1) {
            repQueue.release(2);
            demQueue.release(1);
            republicans -= 2;
            democrats -= 1;
        }
        lock.unlock();

        myQueue.acquire();       // Wait until part of a group
        // System.out.println(Thread.currentThread().getName() + " voted.");
        vote.run();              // Vote
        // onVoteComplete(); // Sync after voting
    }
    public void onVoteComplete() throws InterruptedException {
        lock.lock();
        try {
            finishedCount++;
            if (finishedCount < 3) {
                // Wait for other group members
                groupDone.await();
            } else {
                // Last voter — reset and wake others
                finishedCount = 0;
                groupDone.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }    
}
