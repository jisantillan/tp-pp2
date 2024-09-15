package org.domingus.polling;

import org.domingus.interfaces.Observable;
import org.domingus.interfaces.Observer;

import java.util.List;

public class Timer implements Observable {
    private Integer timeInterval;

    public Timer(Integer timeInterval){
        this.timeInterval = timeInterval;
    }

    public void start() throws InterruptedException { //TODO revisar si debe ser privado
        while (true) {
            Thread.sleep(this.timeInterval);
            notifyObservers();
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
    public void notifyObservers() {
        observers.forEach((observer -> observer.update()));
    }

    @Override
    public void notifyObservers(List<String> list) {

    }
}
