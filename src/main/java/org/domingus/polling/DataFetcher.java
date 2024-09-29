package org.domingus.polling;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;
import org.domingus.mock.Mock;

public class DataFetcher implements Observer, Observable {

    private URL url;
    private Mock mock;
    private Set<Observer> observers;
    
    public DataFetcher( URL url){
        observers = new HashSet<>();
        this.url = url;
        mock = new Mock();
    }

    private Data fetchData()  {
        //TODO resolve data url
        return mock.getData(url.toString());
    }

    @Override
    public void update(Object arg)  {
        this.notifyObservers(arg);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object arg) {
        observers.forEach((observer -> observer.update(fetchData())));
    }
}



