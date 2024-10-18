package org.domingus.app;

import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;

import java.util.HashSet;
import java.util.Set;

public class Domingus implements Observable {

    private Set<Observer> observers;

    public Domingus() {
            this.observers = new HashSet<>();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyAll(String message) {
        observers.forEach(observer -> observer.update(message));
    }

}