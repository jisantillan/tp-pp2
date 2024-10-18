package org.domingus.init;

import org.domingus.app.*;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.Source;

import java.io.FileNotFoundException;
import java.util.Set;

public class DomingusFactory {

    private Discoverer discoverer;

    public DomingusFactory(){
        this.discoverer = new Discoverer();
    }

    public Domingus create(Source source, String extensionPath) throws FileNotFoundException {

        Set<Observer> observers = discoverer.discover(extensionPath);
        Dispatcher dispatcher = createDispatcher(observers);

        MessageAdapter messageAdapter = new MessageAdapter(dispatcher);
        ChangeDetector changeDetector = new ChangeDetector(messageAdapter);
        ClassroomAssignmentHistory classroomAssignmentHistory = new ClassroomAssignmentHistory(changeDetector);
        SourceListener sourceListener = new SourceListener(classroomAssignmentHistory);

        if (source != null) {
            source.suscribe(sourceListener);
        }

        return new Domingus(dispatcher);
    }

    private Dispatcher createDispatcher(Set<Observer> observers) {
        Dispatcher dispatcher = new Dispatcher();
        observers.forEach(dispatcher::addObserver);
        return dispatcher;
    }

}
