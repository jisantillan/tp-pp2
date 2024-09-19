package org.domingus.interfaces;

import java.util.HashSet;
import java.util.Set;

public interface Observable {

    Set<Observer> observers = new HashSet<>();

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Object arg);
}
