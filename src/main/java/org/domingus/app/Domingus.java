package org.domingus.app;

import org.domingus.interfaces.Notifier;
import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Domingus implements Observable {

    private Set<Observer> observers;
    private Set<Observer> currentObservers;

    public Domingus() {
            this.observers = new HashSet<>();
            this.currentObservers = new HashSet<>();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void addCurrentObserver(Observer observer) {
        synchronized (currentObservers) {
            this.currentObservers.add(observer);
        }
    }

    @Override
    public void notify(String message) {
        synchronized (currentObservers) {
            currentObservers.forEach(observer -> observer.update(message));
        }
    }

    public void removeCurrentObserver(Observer observer){
        synchronized (currentObservers) {
            this.currentObservers.remove(observer);
        }
    }

    public Set<Observer> getObservers() {
        return Set.copyOf(observers);
    }

    public Set<Observer> getCurrentObservers() {
        return Set.copyOf(currentObservers);
    }
}