package org.domingus.polling;

import org.domingus.interfaces.ChangeInform;
import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ChangeDetector implements ChangeInform, Observable {
	
    private Set<Observer> observers;
    
    public ChangeDetector() {
    	observers = new HashSet<>();
	}


    @Override
    public void inform(Data data, Data data2) {
        if (hasDifferences(data, data2)) {
            Map<String, List<String>> changes = detectChanges(data, data2);
            notifyObservers(changes);
        }
    }

    private boolean hasDifferences(Data data , Data data2){
        return data.hasChanges(data2) && data.sameName(data2);
    }

    // Este metodo es redundante, se puede llamar directamente a data.detectChanges(data2);
    private Map<String,List<String>> detectChanges(Data data, Data data2) {
        return data.detectChanges(data2);
    }


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object arg) {
        if (arg instanceof Map) {
            @SuppressWarnings("unchecked")
			Map<String,List<String>> changes = (Map<String,List<String>>) arg;
            observers.forEach((observer -> observer.update(changes)));
        }

    }


}
