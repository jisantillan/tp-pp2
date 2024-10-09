package org.domingus.app;

public class AcademicOfferDiffer {
    public boolean hasDifferences(AcademicOffer academicOffer, AcademicOffer otherAcademicOffer) {
        return academicOffer.getVersion() != otherAcademicOffer.getVersion();
    }

}
