package org.domingus.app;

import java.util.List;
import java.util.Map;

public class MessageAdapter {

    private Notifier notifier;
    public MessageAdapter(Notifier notifier){
        this.notifier = notifier;
    }

    public void execute(List<String> changes){
        notifier.notify(generateMessage(changes));
    }
    private String generateMessage(List<String> changes){
        StringBuilder sb = new StringBuilder();

        for (String change : changes) {
            sb.append("Se ha detectado un cambio en la Oferta Academica: versión nueva nro:").append(change).append("\n");
        }

        return sb.toString();
    }
}
