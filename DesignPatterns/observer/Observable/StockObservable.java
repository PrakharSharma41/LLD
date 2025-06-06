package DesignPatterns.observer.Observable;
import DesignPatterns.observer.Observer.NotificationAlertObserver;

public interface StockObservable {
    public void add(NotificationAlertObserver obs);
    public void remove(NotificationAlertObserver obs);
    public void notifySubs();
    public void setCount(int count);
    public int getCount();
}
