package org.domingus.app;

import java.util.HashSet;
import java.util.Set;

import org.domingus.interfaces.Observer;

public class Dispatcher {

	Set<Observer> observers = new HashSet<>();
	
    public Dispatcher(){}

    public void addObserver(Observer observer) {

        observers.add(observer);
    }

    public void dispatch(String message) {
        observers.forEach(observer -> observer.update(message));
    }

}
