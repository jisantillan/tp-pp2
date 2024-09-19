package org.domingus.mock;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Mock {
    private Map<String, DataMock> mocks;
    private DataMock lastDeliveryData;

    public Mock() {
        mocks = new HashMap<>();
        initializeMocks();
    }

    private void initializeMocks() {
        mocks.put("http://www.ungs.com/aulas.pdf", new DataMock("Aulas_UNGS", new Date().toString()));
    }

    public DataMock getData(String URL)  {
    	if (lastDeliveryData == null) {
    		lastDeliveryData = mocks.get(URL);
    	}
        DataMock newDataMock = new DataMock(lastDeliveryData.getName(), lastDeliveryData.getDate());
        newDataMock.updateVersion();
        lastDeliveryData = newDataMock;

        // Devuelve el dato solicitado (eventualmente con una version nueva)
        return newDataMock;
    }
}
