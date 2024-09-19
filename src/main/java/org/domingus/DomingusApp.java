package org.domingus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.domingus.app.AcademicOffer;
import org.domingus.app.Notifier;
import org.domingus.config.DataFetcherConfiguration;
import org.domingus.config.DataFetcherTypes;
import org.domingus.config.DomingusConfiguration;
import org.domingus.polling.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public class DomingusApp {

    private static final String INITIALIZING_FAIL_MESSAGE = "There is not a configuration for the app";
    private static final String DEFAULT_CONFIGURATION_PATH_FILE = "src\\main\\resources\\configuration\\configuration.json";

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        DomingusConfiguration config = !(args.length == 0) ? getConfigFromArgs(args[0]) : getDefaultConfig();

        if(isNull(config)) {
            throw new RuntimeException(INITIALIZING_FAIL_MESSAGE);
        }

        Notifier notifier = new Notifier();

        ChangeDetector changeDetector = new ChangeDetector();
        changeDetector.addObserver(notifier);

        Set<DataFetcher> dataFetchers = getDataFetchers(config, changeDetector);

        Timer timer = new Timer(config.getTimerInterval());
        dataFetchers.forEach(timer::addObserver);
        timer.start();
    }

    private static Set<DataFetcher> getDataFetchers(DomingusConfiguration config, ChangeDetector changeDetector) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MalformedURLException {
        Set<DataFetcher> dataFetchers = new HashSet<>();
        for (DataFetcherConfiguration defaultData : config.getDataConfiguration()) {
            String dataClassName = DataFetcherTypes.getClassNameByName(defaultData.getName());
            Class<?> clazz = Class.forName(dataClassName);
            Data data = (Data) clazz.getDeclaredConstructor().newInstance();
            data.setDate(defaultData.getDate());
            data.setName(defaultData.getName());
            VersionHistory versionsHistory = new VersionHistory(changeDetector,data);
            DataFetcher dataFetcher = new DataFetcher(versionsHistory, new URL(defaultData.getUrl()));
            dataFetchers.add(dataFetcher);
        }
        return dataFetchers;
    }

    private static DomingusConfiguration getDefaultConfig() throws IOException {
       return getConfigFromArgs(DEFAULT_CONFIGURATION_PATH_FILE);
    }

    private static DomingusConfiguration getConfigFromArgs(String path) throws IOException {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, DomingusConfiguration.class);
    }
}