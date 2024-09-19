package org.domingus.interfaces;

import java.util.List;
import java.util.Map;

public interface Comparable {
    Map<String,List<String>> detectChanges(Comparable other);
    boolean hasChanges(Comparable other);
}
