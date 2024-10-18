package org.domingus.app;

import org.domingus.interfaces.Observer;

public class Domingus {

    private Dispatcher dispatcher;

    public Domingus( Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
    }

    public void addObserver(Observer observer) {
        dispatcher.addObserver(observer);
    }

}