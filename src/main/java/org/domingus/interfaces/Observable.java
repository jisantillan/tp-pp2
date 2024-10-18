package org.domingus.interfaces;

public interface Observable {
    void addObserver(Observer observer);

    void notify(String message);

}
