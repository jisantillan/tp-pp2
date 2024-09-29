package org.domingus.interfaces;

import org.domingus.polling.Data;

import java.util.List;
import java.util.Map;

public interface Comparable {
    Map<String, List<String>> compare(Data data);
}
