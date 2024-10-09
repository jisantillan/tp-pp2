package org.domingus.app;

import java.util.List;

public class MessageAdapter {

    private Dispatcher dispatcher;
    public MessageAdapter(Dispatcher dispatcher){
        this.dispatcher = dispatcher;
    }

    public void execute(List<String> changes){
        dispatcher.dispatch(generateMessage(changes));
    }
    private String generateMessage(List<String> changes){
        StringBuilder sb = new StringBuilder();

        for (String change : changes) {
            sb.append("Se han detectado cambios en la oferta académica, nueva versión nro: ").append(change).append("\n");
        }

        return sb.toString();
    }
}
