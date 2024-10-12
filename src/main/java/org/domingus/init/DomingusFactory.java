package org.domingus.init;

import org.domingus.app.ChangeDetector;
import org.domingus.app.Dispatcher;
import org.domingus.app.Domingus;
import org.domingus.app.MessageAdapter;
import org.domingus.interfaces.Notifier;
import org.domingus.interfaces.Source;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Set;

public class DomingusFactory {

    private Discoverer discoverer;

    public DomingusFactory(){
        this.discoverer = new Discoverer();
    }

    public Domingus create(Source source, String extensionPath) throws FileNotFoundException {

        Set<Notifier> notifiers = discoverer.discover(extensionPath);
        Dispatcher dispatcher = new Dispatcher();

        MessageAdapter messageAdapter = new MessageAdapter(dispatcher);
        ChangeDetector changeDetector = new ChangeDetector(messageAdapter);

        if (Objects.nonNull(source)) {
            source.suscribe(changeDetector);
        }

        return new Domingus(dispatcher, notifiers);
    }

}
