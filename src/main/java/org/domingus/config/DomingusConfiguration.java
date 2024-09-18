package org.domingus.config;

import org.domingus.polling.Data;

import java.util.List;

public class DomingusConfiguration {

    private List<Data> defaultData;
    private Integer timerInterval;

    public DomingusConfiguration() {
    }

    public DomingusConfiguration(List<Data> defaultData, Integer timerInterval) {
        this.defaultData = defaultData;
        this.timerInterval = timerInterval;
    }

    public List<Data> getDefaultData() {
        return defaultData;
    }

    public Integer getTimerInterval() {
        return timerInterval;
    }
}
