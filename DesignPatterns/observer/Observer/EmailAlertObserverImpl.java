package DesignPatterns.observer.Observer;
import DesignPatterns.observer.Observable.StockObservable;

public class EmailAlertObserverImpl implements NotificationAlertObserver{
    String email;
    StockObservable Observable;
    
    @Override
    public void update() {
        sendMail(email,"product is in stock");
    }

    private void sendMail(String email2, String string) {
        System.out.println("in stock");
    }

    public EmailAlertObserverImpl(String email, StockObservable observable) {
        this.email = email;
        Observable = observable;
    }
    
}
