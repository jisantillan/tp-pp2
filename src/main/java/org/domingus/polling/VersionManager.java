package org.domingus.polling;

import org.domingus.app.AcademicOffer;
import org.domingus.interfaces.ChangeInform;
import org.domingus.interfaces.VersionUpdater;

public class VersionManager implements VersionUpdater {

    private Data lastVersion;
    private ChangeInform changeInform;
    public VersionManager (ChangeInform changeInform){
        this.changeInform = changeInform;
    }

    @Override
    public void updateVersion(Data data) {
        lastVersion = new AcademicOffer("", "" , "");
        changeInform.inform(this.lastVersion, data);
        this.lastVersion =  data;
    }


}
