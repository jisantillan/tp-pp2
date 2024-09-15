package org.domingus.interfaces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Observable {

    public Set<Observer> observers = new HashSet<>();
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
    public void notifyObservers(List<String> list);
}
