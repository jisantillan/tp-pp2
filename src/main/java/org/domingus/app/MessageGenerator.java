package org.domingus.app;

import java.util.List;
import java.util.Map;

public class MessageGenerator {

    private Map<String,List<String>> changes;
    public MessageGenerator(Map<String,List<String>> changes){
        this.changes = changes;
    }

    public String generateMessage(){
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, List<String>> entry : changes.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();

            sb.append("Se ha detectado un cambio en ").append(key).append(": ");

            sb.append(String.join(", ", values));

            sb.append("\n");
        }

        return sb.toString();
    }
}
