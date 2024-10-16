import org.domingus.app.Domingus;
import org.domingus.init.DomingusFactory;
import org.domingus.interfaces.Notifier;

import java.io.FileNotFoundException;

public class MainTestEmptySource implements Notifier {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Iniciando DomingusApp");

        DomingusFactory factory = new DomingusFactory();
        Domingus domingus = factory.create(null, "src\\main\\resources\\extensions\\");

        domingus.addNotifier(new MainTest());

    }

    @Override
    public void notify(String message) {
        System.out.println("\nHa llegado una notificación");
        System.out.println(message);
    }

    @Override
    public String getName() {
        return "testName";
    }
}
