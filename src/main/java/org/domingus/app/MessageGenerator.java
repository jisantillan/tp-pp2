package org.domingus.app;

import java.util.List;

public class MessageGenerator {

    private List<String> changes;
    public MessageGenerator(List<String> changes){
        this.changes = changes;
    }


    public String generateMessage(){
        // TODO
        // Formatear una respuesta, puede ser usando StringBuilder.
        return "changes modificados";
    }
}
