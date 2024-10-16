package org.domingus.app;

import org.domingus.interfaces.Notifier;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Domingus {

    private Dispatcher dispatcher;

    private Map<String, Notifier> allNotifiers;
    private Set<Notifier> currentNotifiers;

    public Domingus( Dispatcher dispatcher, Set<Notifier> notifiers) {
        this.dispatcher = dispatcher;
        this.allNotifiers = setAllNotifiers(notifiers);
        this.currentNotifiers = new HashSet<>();
    }

    public void addNotifier(Notifier notifier) {
        allNotifiers.put(notifier.getName(), notifier);
    }

    public void addCurrentNotifier(String name){
        currentNotifiers.add(allNotifiers.get(name));
        dispatcher.setNotifiers(currentNotifiers);
    }

    public Set<String> getAllNotifiersNames(){
        return allNotifiers.keySet();
    }

    public Set<String> getCurrentNotifiersNames(){
        return currentNotifiers.stream().map(Notifier::getName).collect(Collectors.toSet());
    }

    private Map<String, Notifier> setAllNotifiers(Set<Notifier> notifiers){
        return notifiers.stream()
                .collect(Collectors.toMap(Notifier::getName, notifier -> notifier));
    }
    public void removeCurrentNotifier(String name){
        currentNotifiers.remove(allNotifiers.get(name));
        dispatcher.setNotifiers(currentNotifiers);
    }

}