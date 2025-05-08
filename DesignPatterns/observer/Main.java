package DesignPatterns.observer;

import DesignPatterns.observer.Observable.IphoneObservableImpl;
import DesignPatterns.observer.Observable.StockObservable;
import DesignPatterns.observer.Observer.EmailAlertObserverImpl;
import DesignPatterns.observer.Observer.NotificationAlertObserver;

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
