package org.domingus.app;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.domingus.interfaces.NotificationPlatform;
import org.domingus.interfaces.Observer;

public class Notifier {

	Set<NotificationPlatform> observers = new HashSet<>();
	
    public Notifier(){}

    public void addObserver(NotificationPlatform observer) {

        observers.add(observer);
    }

    public void notify(String message) {
        observers.forEach( notificationPlatform -> notificationPlatform.sendMessage(message));
    }

}
