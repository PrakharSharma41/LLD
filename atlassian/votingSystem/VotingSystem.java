import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

public class VotingSystem {
    private int democrats = 0;
    private int republicans = 0;

    private final Lock lock = new ReentrantLock();
    private final Semaphore demQueue = new Semaphore(0);
    private final Semaphore repQueue = new Semaphore(0);

    // Synchronization for group to vote together
    private int readyToVote = 0;
    private final Condition groupDone = lock.newCondition();

    public void democrat(Runnable vote) throws InterruptedException {
        lock.lock();
        democrats++;
        if (democrats >= 3) {
            demQueue.release(3);
            democrats -= 3;
        } else if (democrats >= 2 && republicans >= 1) {
            demQueue.release(2);
            repQueue.release(1);
            democrats -= 2;
            republicans -= 1;
        } 
        
        // else {
            lock.unlock();  // no group yet
        // }

        demQueue.acquire();      // Wait until group is formed
        vote.run();              // Cast vote
        onVoteComplete();        // Wait for rest of group
    }

    public void republican(Runnable vote) throws InterruptedException {
        lock.lock();
        republicans++;
        if (republicans >= 3) {
            repQueue.release(3);
            republicans -= 3;
        } else if (republicans >= 2 && democrats >= 1) {
            repQueue.release(2);
            demQueue.release(1);
            republicans -= 2;
            democrats -= 1;
        } else {
            lock.unlock();  // no group yet
        }

        repQueue.acquire();      // Wait until group is formed
        vote.run();              // Cast vote
        onVoteComplete();        // Wait for rest of group
    }

    private void onVoteComplete() throws InterruptedException {
        lock.lock();
        readyToVote++;
        if (readyToVote == 3) {
            // Last voter signals others to continue
            readyToVote = 0;
            groupDone.signalAll();
            lock.unlock();
        } else {
            // Wait for others to complete
            while (readyToVote != 0) {
                groupDone.await();
            }
            lock.unlock();
        }
    }
}
