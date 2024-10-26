package org.domingus.app;

public class Dispatcher {

    private Domingus domingus;

    public Dispatcher(Domingus domingus){
        this.domingus = domingus;
    }

    public void dispatch(String message){
        domingus.getCurrentObservers().forEach(observer -> observer.update(message));
    }
}
