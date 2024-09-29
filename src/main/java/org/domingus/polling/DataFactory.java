package org.domingus.polling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.domingus.config.DataFetcherTypes;

public class DataFactory {

    private final ObjectMapper objectMapper;

    public DataFactory() {
        this.objectMapper = new ObjectMapper();
    }

    public AcademicData createData(String dataType, String body) throws ClassNotFoundException, JsonProcessingException {

        String className = DataFetcherTypes.getClassNameByName(dataType);

        Class<?> dataClass = Class.forName(className);
        AcademicData academicData = (AcademicData) objectMapper.readValue(body, dataClass );

        return academicData;
    }

}


