package org.domingus.polling;

import org.domingus.app.AcademicOffer;

import java.util.Map;

public class DataFactory {

    public static Data createDataFromMap(String url, Map<String, Object> dataMap) {

        //TODO resolver hardcodeo de name y date
        String name = (String) dataMap.get("name");
        String date = (String) dataMap.get("date");

        switch (url) {
            case "http://localhost:8081/accademic-offer":     //TODO resolver hardcodeo
                return new AcademicOffer(name, date);
            default:
                throw new IllegalArgumentException("URL no reconocida: " + url);
        }
    }

}
