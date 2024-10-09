package org.domingus.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import java.util.Set;

import org.domingus.init.Discoverer;
import org.domingus.interfaces.Notifier;
import org.domingus.interfaces.Source;

public class Domingus {

    private Dispatcher dispatcher;
    private Source source;
    
    public Domingus(Source source) {
            this.source = source;
            this.dispatcher = new Dispatcher();
    }

    public void init(String extensionPath) throws InterruptedException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (extensionPath != null && !extensionPath.isEmpty()){
            Discoverer discoverer = new Discoverer();
            Set<Notifier> platforms = discoverer.discover(extensionPath);
            platforms.forEach(dispatcher::addNotifier);
        }
        MessageAdapter messageAdapter = new MessageAdapter(dispatcher);
        ChangeDetector changeDetector = new ChangeDetector(messageAdapter);
        if (source != null) {
            source.suscribe(changeDetector);
        }
    }

    public void addObserver(Notifier observer) {
        dispatcher.addNotifier(observer);
    }
    
}