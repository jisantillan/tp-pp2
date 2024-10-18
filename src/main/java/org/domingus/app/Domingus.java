package org.domingus.app;

import org.domingus.interfaces.Observer;

import java.util.HashSet;
import java.util.Set;

public class Domingus {

    private Set<Observer> observers;

    public Domingus() {
            this.observers = new HashSet<>();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void notifyAll(String message) {
        observers.forEach(observer -> observer.update(message));
    }

}