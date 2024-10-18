package org.domingus.app;

import org.domingus.interfaces.Notifier;
import org.domingus.interfaces.Observer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Domingus {

    private Set<Observer> observers;
    private Set<Observer> currentObservers;

    public Domingus() {
            this.observers = new HashSet<>();
            this.currentObservers = new HashSet<>();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void addCurrentObserver(Observer observer) {
        this.currentObservers.add(observer);
    }

    public void notify(String message) {
        currentObservers.forEach(observer -> observer.update(message));
    }

    public void removeCurrentObserver(Observer observer){
        this.currentObservers.remove(observer);
    }


    public Set<Observer> getCurrentNotifiers(){
        return currentObservers.stream()
                .filter(observer -> observer.getClass().isAnnotationPresent(Notifier.class))
                .collect(Collectors.toSet());
    }

    public Set<Observer> getNotifiers(){
        return observers.stream()
                .filter(observer -> observer.getClass().isAnnotationPresent(Notifier.class))
                .collect(Collectors.toSet());
    }
}