package org.domingus.test;

import java.util.List;

public class DomingusConfiguration {

    private Integer timerInterval;
    private String extensionsPath;

    public DomingusConfiguration() {
    }

    public DomingusConfiguration(Integer timerInterval, String extensionsPath) {
        this.timerInterval = timerInterval;
        this.extensionsPath = extensionsPath;
    }


    public Integer getTimerInterval() {
        return timerInterval;
    }

    public String getExtensionsPath() {
        return extensionsPath;
    }
}
