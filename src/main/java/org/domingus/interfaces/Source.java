package org.domingus.interfaces;

import org.domingus.app.AcademicOffer;

public interface Source {
    void suscribe(Observer observer);
    void send(AcademicOffer academicOffer);
}
