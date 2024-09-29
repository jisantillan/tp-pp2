package org.domingus.polling;

import org.domingus.interfaces.Comparable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Data implements Comparable {
    protected String name;
    protected String date;

    public Data() {
    }

    public Data(String name, String date) {
        this.name = name;
        this.date = date;
    }


    @Override
    public Map<String, List<String>> compare(Data other) {
        Map<String, List<String>> result = null;

        List<String> changes = new ArrayList<>();


        if (!this.date.equals(other.date)) {
            result = new HashMap<>();
            changes.add(other.date);
            result.put(other.name, changes);
        }

        return result;

    }

    // este metodo es redundante
    protected boolean sameName(Data otherData) {
        return this.name.equals(otherData.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
