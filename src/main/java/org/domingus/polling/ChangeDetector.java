package org.domingus.polling;

import org.domingus.interfaces.ChangeInform;
import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;

import java.util.List;


public class ChangeDetector implements ChangeInform, Observable {

    @Override
    public void inform(Data data, Data data2) {
        List<String> changes = detectChanges(data,data2);
        notifyObservers(changes);
    }

    private List<String> detectChanges(Data data, Data data2) {
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
    public void notifyObservers() {
        observers.forEach((observer -> observer.update()));
    }

    @Override
    public void notifyObservers(List<String> changes) {
        observers.forEach((observer -> observer.update(changes)));
    }

}
