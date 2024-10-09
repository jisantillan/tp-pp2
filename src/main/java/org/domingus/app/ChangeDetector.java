package org.domingus.app;

import org.domingus.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class ChangeDetector implements Observer {

    private AcademicOffer lastVersion;
    private MessageAdapter messageAdapter;
    private AcademicOfferDiffer comparator;
    public ChangeDetector(MessageAdapter messageAdapter) {
        this.messageAdapter = messageAdapter;
        this.comparator = new AcademicOfferDiffer();
	}

    @Override
    public void update(Object arg) {
        AcademicOffer newVersion = (AcademicOffer) arg;
        if (lastVersion != null && comparator.hasDifferences(lastVersion, newVersion)) {
            messageAdapter.execute(listDifferences(newVersion, lastVersion));
        }
        lastVersion = newVersion;
    }

    private List<String> listDifferences(AcademicOffer newVersion, AcademicOffer lastVersion){
        List<String> differences = new ArrayList<>();
        if (newVersion.getVersion() != lastVersion.getVersion()) {
            differences.add(newVersion.getVersion().toString());
        }
        return differences ;
    }
}
