package DesignPatterns.observer.Observable;

import java.util.ArrayList;
import java.util.List;

import DesignPatterns.observer.Observer.NotificationAlertObserver;

public class IphoneObservableImpl implements StockObservable{
    public List<NotificationAlertObserver> observelist=new ArrayList<>();
    public int count=0;
    @Override
    public void add(NotificationAlertObserver obs) {
        observelist.add(obs);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void remove(NotificationAlertObserver obs) {
        observelist.remove(obs);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void notifySubs() {
        for(NotificationAlertObserver obs: observelist){
            obs.update();
        }
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifySubs'");
    }

    @Override
    public void setCount(int count) {
        if(this.count==0){
            notifySubs();
        }
        this.count+=count;
    }

    @Override
    public int getCount() {
        return count;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getCount'");
    }
    
}
