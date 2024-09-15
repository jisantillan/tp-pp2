package org.domingus.polling;

import org.domingus.interfaces.Runnable;

public class VersionManager implements Runnable {

    private Data lastVersion;
    private Runnable runnable;
    public VersionManager (Runnable runnable){
        this.runnable = runnable;
    }

    @Override
    public void run(Object o) {
        lastVersion = new Data("", "", "");
        runnable.run(this.lastVersion, o);
        this.lastVersion = (Data) o;
    }

    @Override
    public void run(Object object, Object object2) {

    }

}
