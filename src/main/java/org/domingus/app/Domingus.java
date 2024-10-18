package org.domingus.app;

import org.domingus.interfaces.Observer;

import java.util.HashSet;
import java.util.Set;

public class Domingus {

    private Set<Observer> observers;
    private Set<Observer> currentObservers;

    public Domingus() {
            this.observers = new HashSet<>();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void addCurrentObserver(Observer observer) {
        this.currentObservers.add(observer);
    }

    public void notifyAll(String message) {
        observers.forEach(observer -> observer.update(message));
    }

    public void removeCurrentObserver(Observer observer){
        this.currentObservers.remove(observer);
    }

    public Set<Observer> getObservers(){
        return observers;
    }

    public Set<Observer> getCurrentObservers(){
        return currentObservers;
    }

}