package textEditor;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

abstract class RopeNode {
    protected final ReadWriteLock lock = new ReentrantReadWriteLock();
    public abstract int length();
    public abstract String getText();
    public abstract void insert(int offset, String text);
    public abstract void delete(int offset, int length);
}