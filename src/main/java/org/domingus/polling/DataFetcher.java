package org.domingus.polling;

import org.domingus.app.AcademicOffer;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.VersionUpdater;

import java.net.URL;

public class DataFetcher implements Observer {

    private VersionUpdater versionUpdater ;
    public DataFetcher(VersionUpdater versionUpdater, URL url){
        this.versionUpdater = versionUpdater;
    }

    private Data fetchData(){
        //TODO resolve data url
        return new AcademicOffer("AcademicOffer",  "newDate");
    }

    @Override
    public void update(Object arg) {
        this.versionUpdater.updateVersion(fetchData());
    }
}



