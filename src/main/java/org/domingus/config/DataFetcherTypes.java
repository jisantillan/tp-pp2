package org.domingus.config;

import org.domingus.app.AcademicOffer;

import static java.util.Objects.isNull;

public enum DataFetcherTypes {

    ACADEMIC_OFFER("Aulas_UNGS", AcademicOffer.class.getName());

    public static final String DATA_NOT_FOUND_MESSAGE = "There are not data fetchers configured for give name %s";
    private String name;
    private String className;

    DataFetcherTypes(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public static String getClassNameByName(String name) {
        DataFetcherTypes type = null;

        for (DataFetcherTypes value : DataFetcherTypes.values()) {
            if(name.equals(value.getName())) {
                type = value;
            }
        }

        if(isNull(type)) throw new IllegalArgumentException(String.format(DATA_NOT_FOUND_MESSAGE, name));

        return type.getClassName();
    }

}
