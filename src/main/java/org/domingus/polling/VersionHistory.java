package org.domingus.polling;

import org.domingus.interfaces.ChangeInform;
import org.domingus.interfaces.Observer;

public class VersionHistory implements Observer {

    private AcademicData currentVersion;
    private AcademicData previousVersion;
    private ChangeInform changeInform;
    
    public VersionHistory(ChangeInform changeInform, AcademicData currentVersion){
        this.currentVersion = currentVersion;
        this.changeInform = changeInform;
    }

    @Override
    public void update(Object arg) {
        previousVersion = currentVersion;
        currentVersion = (AcademicData) arg;
        changeInform.inform( currentVersion, previousVersion);
    }
}
