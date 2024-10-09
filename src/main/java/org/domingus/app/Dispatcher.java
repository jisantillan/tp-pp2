package org.domingus.app;

import java.util.HashSet;
import java.util.Set;

import org.domingus.interfaces.Notifier;

public class Dispatcher {

	Set<Notifier> notifiers = new HashSet<>();
	
    public Dispatcher(){}

    public void addNotifier(Notifier observer) {

        notifiers.add(observer);
    }

    public void dispatch(String message) {
        notifiers.forEach(notifier -> notifier.notify(message));
    }

}
