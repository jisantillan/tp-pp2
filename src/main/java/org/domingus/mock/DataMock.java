package org.domingus.mock;

import org.domingus.polling.Data;

import java.util.Date;
import java.util.Random;

public class DataMock extends Data {

    private int version;
    private Random random;

    public DataMock(String name, String date) {
        super(name, date);
        this.version = 1;
        this.random = new Random();
    }

    public void updateVersion() {
        // Hay un 30% de probabilidad de que la version del archivo haya cambiado
        if (random.nextInt(100) < 30) {
            version++;
            date = new Date().toString();
        }
    }

}