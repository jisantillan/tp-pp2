package org.domingus;

import org.domingus.app.Notifier;
import org.domingus.polling.*;

public class DomingusApp {
    public static void main(String[] args) throws InterruptedException {
        Notifier notifier = new Notifier();

        Data data = new Data("AcademicOffer", "www.blabla", "dateMock");

        ChangeDetector changeDetector = new ChangeDetector();
        changeDetector.addObserver(notifier);

        VersionManager versionsManager = new VersionManager(changeDetector);

        DataFetcher dataFetcher = new DataFetcher(versionsManager, data);

        Timer timer =  new Timer(2000);
        timer.addObserver(dataFetcher);

        timer.start();
    }
}