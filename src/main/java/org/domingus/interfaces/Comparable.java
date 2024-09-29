package org.domingus.interfaces;

import org.domingus.polling.AcademicData;

import java.util.List;
import java.util.Map;

public interface Comparable {
    Map<String, List<String>> compare(AcademicData academicData);
}
