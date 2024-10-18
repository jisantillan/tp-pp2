
import java.util.*;

import org.domingus.app.ClassroomAssignment;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.Source;

public class DataSender implements Source, Runnable {

    private Set<Observer> observers;
    private int version;

    public DataSender(Integer timeInterval) {
        observers = new HashSet<>();
        Timer timer = new Timer(timeInterval, this);
        Thread thread = new Thread(timer);
        thread.start();
    }

    @Override
    public void suscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void send(ClassroomAssignment classroomAssignment) {
        observers.forEach((observer -> observer.update(classroomAssignment)));
    }

    @Override
    public void run() {
        send(new ClassroomAssignment(version++));
    }
}



