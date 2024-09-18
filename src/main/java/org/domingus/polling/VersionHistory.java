package org.domingus.polling;

import org.domingus.interfaces.ChangeInform;
import org.domingus.interfaces.VersionUpdater;

public class VersionHistory implements VersionUpdater {

    private Data currentVersion;
    private Data previousVersion;
    private ChangeInform changeInform;
    public VersionHistory(ChangeInform changeInform, Data currentVersion){
        this.currentVersion = currentVersion;
        this.changeInform = changeInform;
    }

    @Override
    public void updateVersion(Data data) {
        previousVersion = currentVersion;
        currentVersion = data;
        changeInform.inform( currentVersion, previousVersion);
    }


}
