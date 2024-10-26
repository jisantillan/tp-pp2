package org.domingus.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;

public class Domingus implements Observable {
    private Map<String, Observer> allObservers;
    private Map<String, Observer> currentObservers;

    public Domingus() {
            this.allObservers = new HashMap<>();
            this.currentObservers = new HashMap<>();
    }

    @Override
    public void addObserver(Observer observer) {
        this.allObservers.put(observer.getClass().getSimpleName(), observer);
    }

    public void addCurrentObserver(String name) {
        this.currentObservers.put(name, allObservers.get(name));
    }

    public void removeCurrentObserver(String name){
        this.currentObservers.remove(name);
    }

    public Set<String> getAllObserversNames() {
        return allObservers.keySet();
    }

    public Set<String> getCurrentObserversNames() {
        return currentObservers.keySet();
    }

    public Set<Observer> getCurrentObservers(){
        return currentObservers.values().stream().collect(Collectors.toSet());
    }
    
}