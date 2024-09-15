package org.domingus.config;

import org.domingus.app.AcademicOffer;
import org.domingus.polling.Data;

public class DomingusConfiguration {

    private AcademicOffer defaultData;
    private Integer timerInterval;

    public DomingusConfiguration() {
    }

    public DomingusConfiguration(AcademicOffer defaultData, Integer timerInterval) {
        this.defaultData = defaultData;
        this.timerInterval = timerInterval;
    }

    public Data getDefaultData() {
        return defaultData;
    }

    public Integer getTimerInterval() {
        return timerInterval;
    }

    public void setDefaultData(AcademicOffer defaultData) {
        this.defaultData = defaultData;
    }

    public void setTimerInterval(Integer timerInterval) {
        this.timerInterval = timerInterval;
    }
}
