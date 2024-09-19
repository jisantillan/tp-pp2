package org.domingus.app;

import org.domingus.init.PlatformDiscoverer;
import org.domingus.interfaces.NotificationPlatform;

import java.io.FileNotFoundException;
import java.util.Set;

public class Domingus {

    private Set<NotificationPlatform> platforms;
    private PlatformDiscoverer discoverer;
    public Domingus(String path) throws FileNotFoundException {
        discoverer = new PlatformDiscoverer();
        this.platforms = discoverer.discover(path);
    }


}
