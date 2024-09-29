package org.domingus.polling;

import org.domingus.interfaces.ChangeInform;
import org.domingus.interfaces.Observer;

public class VersionHistory implements Observer {

    private Data currentVersion;
    private Data previousVersion;
    private ChangeInform changeInform;
    
    public VersionHistory(ChangeInform changeInform, Data currentVersion){
        this.currentVersion = currentVersion;
        this.changeInform = changeInform;
    }

    @Override
    public void update(Object arg) {
        previousVersion = currentVersion;
        currentVersion = (Data) arg;
        changeInform.inform( currentVersion, previousVersion);
    }
}
