
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class RandomLockerImpl implements LockerStrategy{

    private final Map<Integer, Locker> freeLocker;

    RandomLockerImpl(Map<Integer, Locker> freeLocker) {
        this.freeLocker = freeLocker;
    }

    @Override
    public Locker selectLocker() {
        if (freeLocker.isEmpty()) {
            throw new RuntimeException("No available lockers!");
        }
        List<Locker> lockers = new ArrayList<>(freeLocker.values());
        return lockers.get(new Random().nextInt(lockers.size()));
    }
}
