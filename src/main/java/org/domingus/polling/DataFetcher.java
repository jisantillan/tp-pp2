package org.domingus.polling;

import org.domingus.app.AcademicOffer;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.VersionUpdater;

import java.util.List;

public class DataFetcher implements Observer {

    private Data data;
    private VersionUpdater versionUpdater ;
    public DataFetcher(VersionUpdater versionUpdater, Data data){
        this.versionUpdater = versionUpdater;
        this.data = data;
    }

    private Data fetchData(){
        //TODO resolve data
        return new AcademicOffer("mockName", "mockUrl", "mockDate");
    }
    @Override
    public void update(List<String> arg) {
    }

    @Override
    public void update() {
        this.versionUpdater.updateVersion(fetchData());
    }
}
