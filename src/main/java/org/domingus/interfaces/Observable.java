package org.domingus.interfaces;

public interface Observable {
    void addObserver(Observer observer);

    void notifyAll(String message);

}
