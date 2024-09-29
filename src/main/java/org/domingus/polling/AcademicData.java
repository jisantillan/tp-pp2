package org.domingus.polling;

import org.domingus.interfaces.Comparable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AcademicData implements Comparable {
    protected String name;
    protected String date;

    public AcademicData() {
    }

    public AcademicData(String name, String date) {
        this.name = name;
        this.date = date;
    }


    @Override
    public Map<String, List<String>> compare(AcademicData other) {
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
    protected boolean sameName(AcademicData otherAcademicData) {
        return this.name.equals(otherAcademicData.name);
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
