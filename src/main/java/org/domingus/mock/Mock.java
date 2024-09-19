package org.domingus.mock;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Mock {
    private Map<String, DataMock> mocks;

    public Mock() {
        mocks = new HashMap<>();
        initializeMocks();
    }

    private void initializeMocks() {
        mocks.put("http://www.ungs.com/aulas.pdf", new DataMock("Aulas_UNGS", new Date().toString()));
    }

    public DataMock getData(String URL)  {
        DataMock newDataMock = new DataMock(mocks.get(URL).getName(), mocks.get(URL).getDate());
        newDataMock.updateVersion();

        // Devuelve el dato solicitado (eventualmente con una version nueva)
        return newDataMock;
    }
}
