package observer;

import observer.Observable.IphoneObservableImpl;
import observer.Observable.StockObservable;
import observer.Observer.EmailAlertObserverImpl;
import observer.Observer.NotificationAlertObserver;

public class Main {
    public static void main(String[] args) {
        StockObservable iphoneStockObservable=new IphoneObservableImpl();
        NotificationAlertObserver observer1= new EmailAlertObserverImpl("jhajhs@gmail.com",iphoneStockObservable);
        NotificationAlertObserver observer2= new EmailAlertObserverImpl("jhajhs@gmail.com",iphoneStockObservable);
        iphoneStockObservable.add(observer2);
        iphoneStockObservable.add(observer1);
        iphoneStockObservable.setCount(10);
    }
}
