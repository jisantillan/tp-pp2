package org.domingus.app;

import org.domingus.interfaces.Notifier;

public class Domingus {

    private Dispatcher dispatcher;

    public Domingus( Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
    }

    public void addObserver(Notifier observer) {
        dispatcher.addNotifier(observer);
    }
    
}