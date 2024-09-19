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
    public Map<String,List<String>> detectChanges(Comparable other) {
        Map<String,List<String>> result = new HashMap<>();

        if (other instanceof Data) {
            Data otherData = (Data) other;
            List<String> changes = new ArrayList<>();

            if (!this.name.equals(otherData.name)) {
                result.put(this.name, new ArrayList<>());
                return result;
            }

            if (!this.date.equals(otherData.date)) {
                changes.add(otherData.date);
            }

            result.put(this.name, changes);

        }

        return result;
    }

    @Override
    public boolean hasChanges(Comparable other) {
        if (other instanceof Data) {
            Data otherData = (Data) other;

            if (!this.date.equals(otherData.date)) {
                return true;
            }
        }

        return false;
        }

    // este metodo es redundante
    protected boolean sameName(Data otherData) {
        if (!this.name.equals(otherData.name)) {
            return false;
        }
        return true;
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
