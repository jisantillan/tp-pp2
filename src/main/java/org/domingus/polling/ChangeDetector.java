package org.domingus.polling;

import org.domingus.interfaces.Comparable;
import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.Runnable;

import java.util.List;


public class ChangeDetector implements Runnable, Observable {

    private void detectChanges(Comparable comparable, Comparable other){
        notifyObservers(comparable.detectChanges(other));
    }

    @Override
    public void run(Object object, Object other) {
        detectChanges((Comparable) object, (Comparable) other);
    }

    @Override
    public void run(Object object) {

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
    public void notifyObservers() {
        observers.forEach((observer -> observer.update()));
    }

    @Override
    public void notifyObservers(List<String> changes) {
        observers.forEach((observer -> observer.update(changes)));
    }

}
