package org.domingus.interfaces;

import org.domingus.app.ClassroomAssignment;

public interface Source {
    void suscribe(Observer observer);
    void send(ClassroomAssignment classroomAssignment);
}
