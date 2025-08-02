import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DiscountManager {
    private static DiscountManager instance;
    private Discount head;
    private final Lock lock = new ReentrantLock();

    private DiscountManager() {
        this.head = null;
    }

    public static synchronized DiscountManager getInstance() {
        if (instance == null) {
            instance = new DiscountManager();
        }
        return instance;
    }

    public void registerDiscount(Discount Discount) {
        lock.lock();
        try {
            if (head == null) {
                head = Discount;
            } else {
                Discount cur = head;
                while (cur.getNext() != null) {
                    cur = cur.getNext();
                }
                cur.setNext(Discount);
            }
        } finally {
            lock.unlock();
        }
    }

    public List<String> getApplicable(Cart cart) {
        lock.lock();
        try {
            List<String> res = new ArrayList<>();
            Discount cur = head;
            while (cur != null) {
                if (cur.isApplicable(cart)) {
                    res.add(cur.name());
                }
                cur = cur.getNext();
            }
            return res;
        } finally {
            lock.unlock();
        }
    }

    public double applyAll(Cart cart) {
        lock.lock();
        try {
            if (head != null) {
                head.applyDiscount(cart);
            }
            return cart.getCurrentTotal();
        } finally {
            lock.unlock();
        }
    }
}