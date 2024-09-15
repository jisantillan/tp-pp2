package org.domingus.polling;

import org.domingus.interfaces.Comparable;

import java.util.ArrayList;
import java.util.List;

public abstract class Data implements Comparable {
    private String name;
    private String url;
    private String date;

    public Data(String name, String url, String date) {
        this.name = name;
        this.url = url;
        this.date = date;
    }

    @Override
    public List<String> detectChanges(Comparable other) {
        return null;
    }
}
