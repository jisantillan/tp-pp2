package org.domingus.config;

import org.domingus.polling.Data;

import java.util.List;

public class DomingusConfiguration {

    private List<DataFetcherConfiguration> dataConfiguration;
    private Integer timerInterval;

    public DomingusConfiguration() {
    }

    public DomingusConfiguration(List<DataFetcherConfiguration> defaultData, Integer timerInterval) {
        this.dataConfiguration = defaultData;
        this.timerInterval = timerInterval;
    }

    public List<DataFetcherConfiguration> getDataConfiguration() {
        return dataConfiguration;
    }

    public Integer getTimerInterval() {
        return timerInterval;
    }
}
