package org.domingus.app;

import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;

import java.util.List;
import java.util.Map;

public class Notifier implements Observer, Observable {

    public Notifier(){}

    @Override
    public void update(Object arg) {
        if (arg instanceof Map) {
            Map<String,List<String>> changes = (Map<String,List<String>>) arg;
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

    @Override
    public void notifyObservers(Object arg) {
        if (arg instanceof Map) {
            Map<String,List<String>> notification = (Map<String,List<String>>) arg;
            MessageGenerator messageGenerator = new MessageGenerator(notification);
            observers.forEach((observer -> messageGenerator.generateMessage()));
            System.out.println(messageGenerator.generateMessage());
        }
    }

}
