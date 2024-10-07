package org.domingus.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import java.util.Set;

import org.domingus.init.PlatformDiscoverer;
import org.domingus.interfaces.NotificationPlatform;
import org.domingus.interfaces.Source;

public class Domingus {

    private Notifier notifier;
    private Source source;
    
    public Domingus(Source source) {
            this.source = source;
            this.notifier = new Notifier();
    }

    public void init(String extensionPath) throws InterruptedException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (extensionPath != null && !extensionPath.isEmpty()){
            PlatformDiscoverer discoverer = new PlatformDiscoverer();
            Set<NotificationPlatform> platforms = discoverer.discover(extensionPath);
            platforms.forEach(notifier::addPlatform);
        }
        MessageAdapter messageAdapter = new MessageAdapter(notifier);
        ChangeDetector changeDetector = new ChangeDetector(messageAdapter);
        if (source != null) {
            source.suscribe(changeDetector);
        }
    }

    public void addObserver(NotificationPlatform observer) {
        notifier.addPlatform(observer);
    }
    
}