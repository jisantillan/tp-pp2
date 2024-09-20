package org.domingus.polling;

import java.net.URL;

import org.domingus.interfaces.Observer;
import org.domingus.interfaces.VersionUpdater;
import org.domingus.mock.Mock;

public class DataFetcher implements Observer {

    private VersionUpdater versionUpdater ;
    private URL url;
    private Mock mock;
    
    public DataFetcher(VersionUpdater versionUpdater, URL url){
        this.versionUpdater = versionUpdater;
        this.url = url;
        mock = new Mock();
    }

    private Data fetchData()  {
        //TODO resolve data url
        return mock.getData(url.toString());
    }

    @Override
    public void update(Object arg)  {
        this.versionUpdater.updateVersion(fetchData());
    }
}



