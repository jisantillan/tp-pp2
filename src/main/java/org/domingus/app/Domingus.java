package org.domingus.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import java.util.Set;

import org.domingus.init.PlatformDiscoverer;
import org.domingus.interfaces.NotificationPlatform;
import org.domingus.interfaces.Source;


public class Domingus {
	
    public static final String INITIALIZING_FAIL_MESSAGE = "There is not a configuration for the app";
    public static final String DEFAULT_CONFIGURATION_PATH_FILE = "src\\main\\resources\\configuration\\configuration.json";

    private Set<NotificationPlatform> platforms;
    private PlatformDiscoverer discoverer;
    
    private Notifier notifier;
    private Source source;
    
    public Domingus(Source source) {
            this.source = source;
            this.notifier = new Notifier();
    }


    public void init(String extensionPath) throws InterruptedException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        discoverer = new PlatformDiscoverer();
        this.platforms = discoverer.discover(extensionPath);
        platforms.forEach(notifier::addPlatform);

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