import org.domingus.app.Domingus;
import org.domingus.init.DomingusFactory;
import org.domingus.interfaces.Observer;

import java.io.FileNotFoundException;

public class MainEmptySource implements Observer {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Iniciando DomingusApp");

        DomingusFactory factory = new DomingusFactory();
        Domingus domingus = factory.create(null, "src\\main\\resources\\extensions\\");

        Main main = new Main();
        domingus.addObserver(main);
        domingus.addCurrentObserver(main.getClass().getSimpleName());
    }

    @Override
    public void update(Object object) {
        System.out.println("\nHa llegado una notificación");
        System.out.println(object);
    }

}
