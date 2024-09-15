package org.domingus.interfaces;

import java.util.List;

public interface Observer {
    void update(List<String> list);
    void update();
}
