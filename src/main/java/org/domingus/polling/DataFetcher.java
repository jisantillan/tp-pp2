package org.domingus.polling;

import org.domingus.interfaces.Observer;
import org.domingus.interfaces.Runnable;

import java.util.List;

public class DataFetcher implements Observer {

    private Data data;
    private Runnable runnable ;
    public DataFetcher(Runnable runnable, Data data){
        this.runnable = runnable;
        this.data = data;
    }

    private Object fetchData(){
        return new Data("mockName", "mockUrl", "mockDate");
    }
    @Override
    public void update(List<String> arg) {
    }

    @Override
    public void update() {
        this.runnable.run(fetchData());
    }
}
