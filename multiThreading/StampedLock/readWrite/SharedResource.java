package StampedLock.readWrite;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    private final StampedLock stampedLock = new StampedLock();
    private final List<Integer> items = new ArrayList<>();
    private final int MAX_CAPACITY = 5;

    public void producer() {
        while (true) {
            long stamp = stampedLock.writeLock();
            try {
                if (items.size() == MAX_CAPACITY) {
                    System.out.println("Producer waiting - list full: " + Thread.currentThread().getName());
                    return;
                }

                int x = (int) (Math.random() * 400);
                System.out.println(Thread.currentThread().getName() + " adding " + x);
                items.add(x);
            } finally {
                stampedLock.unlockWrite(stamp);
            }

            try {
                Thread.sleep(1000); // simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consume() {
        while (true) {
            long readStamp = stampedLock.readLock();
            int size;
            try {
                size = items.size();
                if (size == 0) {
                    System.out.println("Consumer waiting - list empty: " + Thread.currentThread().getName());
                    continue;
                }
            } finally {
                stampedLock.unlockRead(readStamp);
            }

            // Need write lock to modify (remove item)
            long writeStamp = stampedLock.writeLock();
            try {
                if (!items.isEmpty()) {
                    int removed = items.remove(items.size() - 1);
                    System.out.println("Item consumed by " + Thread.currentThread().getName() + ": " + removed);
                }
            } finally {
                stampedLock.unlockWrite(writeStamp);
            }

            try {
                Thread.sleep(1000); // simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
