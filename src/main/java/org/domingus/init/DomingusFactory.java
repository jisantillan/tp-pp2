package org.domingus.init;

import org.domingus.app.ChangeDetector;
import org.domingus.app.Dispatcher;
import org.domingus.app.Domingus;
import org.domingus.app.MessageAdapter;
import org.domingus.interfaces.Notifier;
import org.domingus.interfaces.Source;

import java.io.FileNotFoundException;
import java.util.Set;

public class DomingusFactory {

    private Discoverer discoverer;

    public DomingusFactory(){
        this.discoverer = new Discoverer();
    }

    public Domingus create(Source source, String extensionPath) throws FileNotFoundException {

        Set<Notifier> notifiers = discoverer.discover(extensionPath);
        Dispatcher dispatcher = new Dispatcher();
        Domingus domingus = new Domingus(dispatcher);
        notifiers.forEach(dispatcher::addNotifier);

        MessageAdapter messageAdapter = new MessageAdapter(dispatcher);
        ChangeDetector changeDetector = new ChangeDetector(messageAdapter);
        if (source != null) {
            source.suscribe(changeDetector);
        }

        return domingus;
    }

}
