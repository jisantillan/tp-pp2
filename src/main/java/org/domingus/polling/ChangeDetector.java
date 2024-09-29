package org.domingus.polling;

import org.domingus.interfaces.ChangeInform;
import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;

import java.util.*;

public class ChangeDetector implements ChangeInform, Observable {
	
    private Set<Observer> observers;
    
    public ChangeDetector() {
    	observers = new HashSet<>();
	}

    @Override
    public void inform(Data newData, Data previousData) {
            Map<String, List<String>> changes = previousData.compare(newData);
            if (changes != null) {
                notifyObservers(changes);
            }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void notifyObservers(Object arg) {
        if (arg instanceof Map) {
            Map<String,List<String>> changes = (Map<String,List<String>>) arg;
            observers.forEach((observer -> observer.update(changes)));
        }
    }

}
