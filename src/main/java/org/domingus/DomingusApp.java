package org.domingus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.domingus.app.AcademicOffer;
import org.domingus.app.Notifier;
import org.domingus.config.DomingusConfiguration;
import org.domingus.polling.*;
import java.io.*;
import java.net.URL;

import static java.util.Objects.isNull;

public class DomingusApp {

    private static final String CONFIG_FILE_PROPERTY = "configFile";

    public static void main(String[] args) throws InterruptedException, IOException {
//        DomingusConfiguration config = null;
//        if(!(args.length == 0)) {
//            config = getConfigFromArgs(args[0]);
//        } else {
//            config = getDefaultConfig();
//        }
//
//        if(isNull(config)) {
//            throw new RuntimeException("There is not a configuration for the app");
//        }

        Notifier notifier = new Notifier();

        ChangeDetector changeDetector = new ChangeDetector();
        changeDetector.addObserver(notifier);

        VersionHistory versionsManager = new VersionHistory(changeDetector, new AcademicOffer("Aulas_UNGS" , ""));

        DataFetcher dataFetcher = new DataFetcher(versionsManager, new URL("http://www.ungs.com/aulas.pdf"));

        Timer timer = new Timer(2000);
        timer.addObserver(dataFetcher);
        timer.start();
    }

    private static DomingusConfiguration getDefaultConfig() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(System.getProperty(CONFIG_FILE_PROPERTY), DomingusConfiguration.class);
    }

    private static DomingusConfiguration getConfigFromArgs(String arg) throws IOException {
        File file = new File(arg);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, DomingusConfiguration.class);
    }
}