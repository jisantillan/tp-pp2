package org.domingus.app;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.domingus.interfaces.NotificationPlatform;
import org.domingus.interfaces.Observer;

public class Notifier implements Observer {

	Set<NotificationPlatform> observers = new HashSet<>();
	
    public Notifier(){}

    @Override
    public void update(Object arg) {
        if (arg instanceof Map) {
			Map<String,List<String>> changes = (Map<String,List<String>>) arg;
            notifyObservers(changes);
        }
    }


    public void addObserver(NotificationPlatform observer) {
        observers.add(observer);
    }

    public void removeObserver(NotificationPlatform observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Object arg) {
        if (arg instanceof Map) {
            Map<String, List<String>> notification = (Map<String, List<String>>) arg;
            MessageGenerator messageGenerator = new MessageGenerator(notification);
            String message = messageGenerator.generateMessage();

            for (NotificationPlatform notificationPlatform : observers) {
                notificationPlatform.sendMessage(message);
            }
        }
    }

}
