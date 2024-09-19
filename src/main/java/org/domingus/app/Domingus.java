package org.domingus.app;

import org.domingus.config.DataFetcherConfiguration;
import org.domingus.config.DataFetcherTypes;
import org.domingus.config.DomingusConfiguration;
import org.domingus.init.PlatformDiscoverer;
import org.domingus.interfaces.Notificable;
import org.domingus.interfaces.NotificationPlatform;
import org.domingus.interfaces.Observer;
import org.domingus.polling.ChangeDetector;
import org.domingus.polling.Data;
import org.domingus.polling.DataFetcher;
import org.domingus.polling.Timer;
import org.domingus.polling.VersionHistory;

import com.fasterxml.jackson.databind.ObjectMapper;

import static java.util.Objects.isNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Domingus {
	
    public static final String INITIALIZING_FAIL_MESSAGE = "There is not a configuration for the app";
    public static final String DEFAULT_CONFIGURATION_PATH_FILE = "src\\main\\resources\\configuration\\configuration.json";

    private Set<NotificationPlatform> platforms;
    private PlatformDiscoverer discoverer;
    
    private Notifier notifier;
    
    public Domingus() {
    	notifier = new Notifier();
    }


    public void init(String[] args, String path) throws InterruptedException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        discoverer = new PlatformDiscoverer();
        //this.platforms = discoverer.discover(path);

        DomingusConfiguration config = !(args.length == 0) ? getConfigFromArgs(args[0]) : getDefaultConfig();

        if(isNull(config)) {
            throw new RuntimeException(Domingus.INITIALIZING_FAIL_MESSAGE);
        }

        ChangeDetector changeDetector = new ChangeDetector();
        changeDetector.addObserver(notifier);

        Set<DataFetcher> dataFetchers = getDataFetchers(config, changeDetector);

        Timer timer = new Timer(config.getTimerInterval());
        dataFetchers.forEach(timer::addObserver);
        timer.start();
    }

    private Set<DataFetcher> getDataFetchers(DomingusConfiguration config, ChangeDetector changeDetector) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MalformedURLException {
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

    private DomingusConfiguration getDefaultConfig() throws IOException {
       return getConfigFromArgs(DEFAULT_CONFIGURATION_PATH_FILE);
    }

    private DomingusConfiguration getConfigFromArgs(String path) throws IOException {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, DomingusConfiguration.class);
    }

    public void addObserver(Notificable observer) {
    	notifier.addObserver(observer);
    }
    
}