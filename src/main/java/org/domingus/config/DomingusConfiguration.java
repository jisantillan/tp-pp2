package org.domingus.config;

import org.domingus.polling.Data;

public class DomingusConfiguration {

    private Data defaultData;
    private Integer timerInterval;

    public DomingusConfiguration() {
    }

    public DomingusConfiguration(Data defaultData, Integer timerInterval) {
        this.defaultData = defaultData;
        this.timerInterval = timerInterval;
    }

    public Data getDefaultData() {
        return defaultData;
    }

    public Integer getTimerInterval() {
        return timerInterval;
    }

    public void setDefaultData(Data defaultData) {
        this.defaultData = defaultData;
    }

    public void setTimerInterval(Integer timerInterval) {
        this.timerInterval = timerInterval;
    }
}
