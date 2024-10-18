package org.domingus.app;

import org.domingus.interfaces.Observer;

public class SourceListener implements Observer {

    private ClassroomAssignmentHistory classroomAssignmentHistory;
    public SourceListener(ClassroomAssignmentHistory classroomAssignmentHistory){
        this.classroomAssignmentHistory = classroomAssignmentHistory;
    }
    @Override
    public void update(Object object) {
        classroomAssignmentHistory.updateLastVersion((ClassroomAssignment) object);
    }
}
