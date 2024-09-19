package org.domingus.init;

import org.domingus.app.Domingus;

import java.io.FileNotFoundException;

public class DomingusFactory {

    public Domingus create (String path) throws FileNotFoundException {
        return new Domingus(path);
    }

}
