package org.domingus.mock;

import org.domingus.polling.Data;

import java.util.Date;
import java.util.Random;

public class DataMock extends Data {

    private static int CHANGE_PROBABILITY = 30;
    private Random random;

    public DataMock(String name, String date) {
        super(name, date);
        this.random = new Random();
    }

    public void updateVersion() {
        if (random.nextInt(100) < CHANGE_PROBABILITY) {
            date = new Date().toString();
        }
    }

}