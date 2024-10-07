package org.domingus.app;

import java.util.HashSet;
import java.util.Set;

import org.domingus.interfaces.NotificationPlatform;

public class Notifier {

	Set<NotificationPlatform> platforms = new HashSet<>();
	
    public Notifier(){}

    public void addPlatform(NotificationPlatform observer) {

        platforms.add(observer);
    }

    public void notify(String message) {
        platforms.forEach(notificationPlatform -> notificationPlatform.sendMessage(message));
    }

}
