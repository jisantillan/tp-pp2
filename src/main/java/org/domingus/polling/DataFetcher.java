package org.domingus.polling;

import java.net.URL;

import java.util.*;

import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;

public class DataFetcher implements Observer, Observable {

    private URL url;
    private Set<Observer> observers;
    private HttpService httpService;

    public DataFetcher( URL url){
        observers = new HashSet<>();
        this.url = url;
        this.httpService = new HttpService();
    }

    private Data fetchData()  {
        return httpService.sendGet(url.toString());
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



