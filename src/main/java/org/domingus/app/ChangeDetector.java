package org.domingus.app;

import java.util.ArrayList;
import java.util.List;

public class ChangeDetector {

    private MessageAdapter messageAdapter;
    public ChangeDetector(MessageAdapter messageAdapter) {
        this.messageAdapter = messageAdapter;
	}

    public void detectChanges(ClassroomAssignment previous, ClassroomAssignment current ) {
        if (previous != null && !current.equals(previous)) {
            messageAdapter.execute(listChanges(current, previous));
        }
    }

    private List<String> listChanges(ClassroomAssignment newVersion, ClassroomAssignment lastVersion){
        List<String> changes = new ArrayList<>();
        if (newVersion.getVersion() != lastVersion.getVersion()) {
            changes.add(newVersion.getVersion().toString());
        }
        return changes ;
    }


}
