package org.domingus.init;

import static java.util.Objects.nonNull;

import java.io.FileNotFoundException;
import java.util.Set;

import org.domingus.app.ChangeDetector;
import org.domingus.app.ClassroomAssignmentHistory;
import org.domingus.app.Domingus;
import org.domingus.app.MessageAdapter;
import org.domingus.app.SourceListener;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.Source;

public class DomingusFactory {

    private Discoverer discoverer;

    public DomingusFactory(){
        this.discoverer = new Discoverer();
    }

    public Domingus create(Source source, String extensionPath) throws FileNotFoundException {
        Set<Observer> observers = discoverer.discover(extensionPath);

        Domingus domingus = new Domingus();
        observers.forEach(domingus::addObserver);

        MessageAdapter messageAdapter = new MessageAdapter(domingus);
        ChangeDetector changeDetector = new ChangeDetector(messageAdapter);
        ClassroomAssignmentHistory classroomAssignmentHistory = new ClassroomAssignmentHistory(changeDetector);
        SourceListener sourceListener = new SourceListener(classroomAssignmentHistory);

        if (nonNull(source)) {
            source.suscribe(sourceListener);
        }

        return domingus;
    }

}