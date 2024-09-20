package org.domingus.config;

import java.util.List;

public class DomingusConfiguration {

    private List<DataFetcherConfiguration> dataConfiguration;
    private Integer timerInterval;
    private String extensionsPath;

    public DomingusConfiguration() {
    }

    public DomingusConfiguration(List<DataFetcherConfiguration> defaultData, Integer timerInterval, String extensionsPath) {
        this.dataConfiguration = defaultData;
        this.timerInterval = timerInterval;
        this.extensionsPath = extensionsPath;
    }

    public List<DataFetcherConfiguration> getDataConfiguration() {
        return dataConfiguration;
    }

    public Integer getTimerInterval() {
        return timerInterval;
    }

    public String getExtensionsPath() {
        return extensionsPath;
    }
}
