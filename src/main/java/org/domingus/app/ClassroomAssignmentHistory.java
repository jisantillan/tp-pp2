package org.domingus.app;

public class ClassroomAssignmentHistory {

    private ClassroomAssignment lastVersion;

    private ChangeDetector changeDetector;

    public ClassroomAssignmentHistory(ChangeDetector changeDetector){
        this.changeDetector = changeDetector;
    }

    public void updateLastVersion(ClassroomAssignment newVersion){
        changeDetector.detectChanges(lastVersion, newVersion );
        this.lastVersion = newVersion;
    }

}
