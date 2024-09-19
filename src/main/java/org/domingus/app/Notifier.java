package org.domingus.app;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.domingus.interfaces.Notificable;
import org.domingus.interfaces.Observer;

public class Notifier implements Observer {

	Set<Notificable> observers = new HashSet<>();
	
    public Notifier(){}

    @Override
    public void update(Object arg) {
        if (arg instanceof Map) {
            @SuppressWarnings("unchecked")
			Map<String,List<String>> changes = (Map<String,List<String>>) arg;
            notifyObservers(changes);
        }
    }


    public void addObserver(Notificable observer) {
        observers.add(observer);
    }

    public void removeObserver(Notificable observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Object arg) {
        @SuppressWarnings("unchecked")
		Map<String,List<String>> notification = (Map<String,List<String>>) arg;
        MessageGenerator messageGenerator = new MessageGenerator(notification);
        String message = messageGenerator.generateMessage();

    	for (Notificable notificable : observers) {
			notificable.sendMessage(message);
		}/*
        if (arg instanceof Map) {
            Map<String,List<String>> notification = (Map<String,List<String>>) arg;
            MessageGenerator messageGenerator = new MessageGenerator(notification);
            observers.forEach((observer -> messageGenerator.generateMessage()));
            System.out.println(messageGenerator.generateMessage());
        }*/
    }

}
