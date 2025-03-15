package amazonLocker;

class ExpirationEntry implements Comparable<ExpirationEntry>{
    private long expiryTime;
    private Locker locker;
    ExpirationEntry(long time,Locker locker){
        this.expiryTime=time;this.locker=locker;
    }
    @Override
    public int compareTo(ExpirationEntry o) {
        return Long.compare(this.expiryTime, o.expiryTime);
    }
    public long getExpiryTime() {
        return expiryTime;
    }
    public Locker getLocker() {
        return locker;
    }
}