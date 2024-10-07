package org.domingus.app;

public class AcademicOfferComparator {
    public boolean hasDifferences(AcademicOffer academicOffer, AcademicOffer otherAcademicOffer) {
        return academicOffer.getVersion() != otherAcademicOffer.getVersion();
    }

}
