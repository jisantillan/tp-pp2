package org.domingus.test;

import java.util.*;

import org.domingus.app.AcademicOffer;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.Source;

public class DataSender implements Source, Runnable {

    private Set<Observer> observers;
    private int version;

    public DataSender(Integer timeInterval) throws InterruptedException {
        observers = new HashSet<>();
        Timer timer = new Timer(timeInterval, this);
        Thread thread = new Thread(timer);
        thread.start();
    }

    @Override
    public void suscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void run() {
        observers.forEach((observer -> observer.update(new AcademicOffer(version++))));
    }
}



