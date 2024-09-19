package org.domingus.mock;

import org.domingus.polling.Data;

import java.util.Date;
import java.util.Random;

public class DataMock extends Data {

    private int version;
    private Random random;

    private String name;
    private String date;

    public DataMock(String name, String date) {
        super(name, date);
        this.name = name;
        this.date = date;
        this.version = 1;
        this.random = new Random();
    }

    public void updateVersion() {
        // Hay un 30% de probabilidad de que la version del archivo haya cambiado
        if (random.nextInt(100) < 101) {
            version++;
            date = new Date().toString();
        }
    }

    public String getDate() {
        return date;
    }
    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

}