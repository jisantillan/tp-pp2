package org.domingus.app;

import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;

import java.util.List;

public class Notifier implements Observer, Observable {

    public Notifier(){}

    @Override
    public void update(List<String> arg) {
        notifyObservers(arg);
    }

    @Override
    public void update() {

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
    public void notifyObservers(List<String> list) {
        MessageGenerator messageGenerator = new MessageGenerator(list);
        observers.forEach((observer -> messageGenerator.generateMessage()));
        System.out.println("se notifico a la UI");
    }
}
